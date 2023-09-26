package com.app.InkaManu.Controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.InkaManu.Configuration.FirebaseConfig;
import com.app.InkaManu.Model.DTO.ProductoDTO;
import com.app.InkaManu.Model.Entity.Producto;
import com.app.InkaManu.Model.Service.ProductoService;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.StorageClient;

@Controller
@RequestMapping("/inkamanu")
public class ProductoController {

    private final ProductoService productoService;

    private final FirebaseConfig firebaseConfig;

    public ProductoController(@Autowired ProductoService productoService, @Autowired FirebaseConfig firebaseConfig) {
        this.productoService = productoService;
        this.firebaseConfig = firebaseConfig;
    }

    @GetMapping(value = "/admin/formularioCrearProducto")
    public String mostrarFormularioCrearNuevoProducto(Model model) {
        model.addAttribute("productoDTO", new ProductoDTO());
        return "formularios/formularioCrearProducto";
    }

    @PostMapping("/admin/guardarProductoNuevo")
    public String guardarProductoNuevo(@Valid @ModelAttribute ProductoDTO productoDTO, BindingResult result,
            Model model, RedirectAttributes redirectAttrs) {

        if (result.hasErrors()) {
            return "formularios/formularioCrearProducto";
        }

        Producto p = new Producto();
        p.setDescripcion(productoDTO.getDescripcion());
        p.setFechaActualizacion(null);
        p.setFechaCreacion(LocalDateTime.now());

        try {
            // Subir Archivo (Imagen)
            Storage storage = StorageClient.getInstance().bucket().getStorage(); //Obtengo la instancia del bucket con el storage que estoy trabajando

            MultipartFile imagen = productoDTO.getImagen(); // Archivo que se subira a Firebase Storage
            String bucketName = "inkamanu-springboot.appspot.com"; // Nombre del bucket en el que se almacenara el archivo
            String filePath = "Imagenes/" + imagen.getOriginalFilename(); // Path de a donde queremos que se suba la imagen (dentro del bucket)
            BlobId blobId = BlobId.of(bucketName, filePath); // Blod Identificador de la imagen
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(imagen.getContentType()).build();
            storage.create(blobInfo, imagen.getBytes()); // Subimos la imagen

            // Obtener link para ver la imagen
            String uid = "HcZoxKHcAMdkY1RGwvXhv0vfyc12"; // Uid del usuario, se genera en el Authenticacion al crear un User
            String token = FirebaseAuth.getInstance().createCustomToken(uid); // Se obtiene un token

            // Creamos el url para ver la imagen
            String downloadUrl = "https://firebasestorage.googleapis.com/v0/b/" + bucketName + "/o/" +
                    URLEncoder.encode(filePath, StandardCharsets.UTF_8) + "?alt=media&token=" + token;
            p.setImagen(downloadUrl); // Colocar Url de la imagen para el objeto que se guardara en la base de datos

        } catch (Exception e) {
            p.setImagen("Default Image");
            System.out.println(e.getMessage());
        }
        p.setNombre(productoDTO.getNombre());
        p.setPrecio(productoDTO.getPrecio());
        p.setStock(productoDTO.getStock());

        productoService.saveProduct(p);
        redirectAttrs.addFlashAttribute("success", "Producto guardado con éxito");

        return "redirect:/inkamanu/admin/formularioCrearProducto";
    }

}
