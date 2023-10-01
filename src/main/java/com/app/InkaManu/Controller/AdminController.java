package com.app.InkaManu.Controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException.NotFound;
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
public class AdminController {

    private final ProductoService productoService;

    private final FirebaseConfig firebaseConfig;

    public AdminController(@Autowired ProductoService productoService, @Autowired FirebaseConfig firebaseConfig) {
        this.productoService = productoService;
        this.firebaseConfig = firebaseConfig;
    }

    @GetMapping(value = "/admin/formularioCrearProducto")
    public String mostrarFormularioCrearNuevoProducto(Model model) {
        model.addAttribute("productoDTO", new ProductoDTO());
        return "formularios/registrar_producto";
    }

    @PostMapping("/admin/guardarProductoNuevo")
    public String guardarProductoNuevo(@Valid @ModelAttribute ProductoDTO productoDTO, BindingResult result,
            Model model, RedirectAttributes redirectAttrs) {

        if (result.hasErrors()) {
            return "formularios/registrar_producto";
        }

        Producto p = new Producto();
        p.setDescripcion(productoDTO.getDescripcion());
        p.setFechaActualizacion(null);
        p.setFechaCreacion(LocalDateTime.now());
        MultipartFile imagen = productoDTO.getImagen();
        if (imagen.isEmpty()) {
            if (imagen.isEmpty()) {
                p.setImagen(
                        "https://firebasestorage.googleapis.com/v0/b/inkamanu-springboot.appspot.com/o/Imagenes%2Fsinfoto.jpg?alt=media&token=eyJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJodHRwczovL2lkZW50aXR5dG9vbGtpdC5nb29nbGVhcGlzLmNvbS9nb29nbGUuaWRlbnRpdHkuaWRlbnRpdHl0b29sa2l0LnYxLklkZW50aXR5VG9vbGtpdCIsImV4cCI6MTY5NjEwNTAyOSwiaWF0IjoxNjk2MTAxNDI5LCJpc3MiOiJmaXJlYmFzZS1hZG1pbnNkay15cmoxMEBpbmthbWFudS1zcHJpbmdib290LmlhbS5nc2VydmljZWFjY291bnQuY29tIiwic3ViIjoiZmlyZWJhc2UtYWRtaW5zZGsteXJqMTBAaW5rYW1hbnUtc3ByaW5nYm9vdC5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsInVpZCI6IkhjWm94S0hjQU1ka1kxUkd3dlhodjB2ZnljMTIifQ.gwKC2ko7WkPGFohByZYvE7eS4x-60GO2vay5XWB08_izQJP4IdOfdd2N7RoMrKMsrm9ustlEiEfKPh2e7IiXAOHlV2cgSgHSKxBvhOlZafD6wVdUbBraSdVnu0F_cvDlloBDpxpVjBetLLH-WrsoQbuiVHX23Jz7CbQeHC8zYepNYXg8HJGb-i7P4iRRc6iD0HmhBElEEIkU4d-G4Yznrm33j6YxWmEOqf4v-DNl4jklF83aV8t97dT_6N1pl8x7c18fIWAUkYFv-ikvxWHGLGLiYJQfImpCxfcwHxWxRsqnaSCYVSRVaXZGZka7fr3nmBMTysrGRg6ajNmH_H8npA");
            }
        } else {
            try {
                // Subir Archivo (Imagen)
                Storage storage = StorageClient.getInstance().bucket().getStorage(); // Obtengo la instancia del bucket
                                                                                     // con
                                                                                     // el storage que estoy trabajando

                // MultipartFile imagen = productoDTO.getImagen(); // Archivo que se subira a
                // Firebase Storage
                String bucketName = "inkamanu-springboot.appspot.com"; // Nombre del bucket en el que se almacenara el
                                                                       // archivo
                String filePath = "Imagenes/" + imagen.getOriginalFilename(); // Path de a donde queremos que se suba la
                                                                              // imagen (dentro del bucket)
                BlobId blobId = BlobId.of(bucketName, filePath); // Blod Identificador de la imagen
                BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(imagen.getContentType()).build();
                storage.create(blobInfo, imagen.getBytes()); // Subimos la imagen

                // Obtener link para ver la imagen
                String uid = "HcZoxKHcAMdkY1RGwvXhv0vfyc12"; // Uid del usuario, se genera en el Authenticacion al crear
                                                             // un
                                                             // User
                String token = FirebaseAuth.getInstance().createCustomToken(uid); // Se obtiene un token

                // Creamos el url para ver la imagen
                String downloadUrl = "https://firebasestorage.googleapis.com/v0/b/" + bucketName + "/o/" +
                        URLEncoder.encode(filePath, StandardCharsets.UTF_8) + "?alt=media&token=" + token;

                p.setImagen(downloadUrl); // Colocar Url de la imagen para el objeto que se guardara en la base de datos

            } catch (Exception e) {
                p.setImagen(
                        "https://firebasestorage.googleapis.com/v0/b/inkamanu-springboot.appspot.com/o/Imagenes%2Fsinfoto.jpg?alt=media&token=eyJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJodHRwczovL2lkZW50aXR5dG9vbGtpdC5nb29nbGVhcGlzLmNvbS9nb29nbGUuaWRlbnRpdHkuaWRlbnRpdHl0b29sa2l0LnYxLklkZW50aXR5VG9vbGtpdCIsImV4cCI6MTY5NjEwNTAyOSwiaWF0IjoxNjk2MTAxNDI5LCJpc3MiOiJmaXJlYmFzZS1hZG1pbnNkay15cmoxMEBpbmthbWFudS1zcHJpbmdib290LmlhbS5nc2VydmljZWFjY291bnQuY29tIiwic3ViIjoiZmlyZWJhc2UtYWRtaW5zZGsteXJqMTBAaW5rYW1hbnUtc3ByaW5nYm9vdC5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsInVpZCI6IkhjWm94S0hjQU1ka1kxUkd3dlhodjB2ZnljMTIifQ.gwKC2ko7WkPGFohByZYvE7eS4x-60GO2vay5XWB08_izQJP4IdOfdd2N7RoMrKMsrm9ustlEiEfKPh2e7IiXAOHlV2cgSgHSKxBvhOlZafD6wVdUbBraSdVnu0F_cvDlloBDpxpVjBetLLH-WrsoQbuiVHX23Jz7CbQeHC8zYepNYXg8HJGb-i7P4iRRc6iD0HmhBElEEIkU4d-G4Yznrm33j6YxWmEOqf4v-DNl4jklF83aV8t97dT_6N1pl8x7c18fIWAUkYFv-ikvxWHGLGLiYJQfImpCxfcwHxWxRsqnaSCYVSRVaXZGZka7fr3nmBMTysrGRg6ajNmH_H8npA");
                System.out.println(e.getMessage());
            }
        }

        p.setGradoAlcohol(productoDTO.getGradoAlcohol());
        p.setTipoCerveza(productoDTO.getTipoCerveza());
        p.setPorcentajeDescuento(productoDTO.getPorcentajeDescuento());
        p.setNombre(productoDTO.getNombre());
        p.setPrecio(productoDTO.getPrecio());
        p.setStock(productoDTO.getStock());

        productoService.saveProduct(p);
        redirectAttrs.addFlashAttribute("success", "Producto Agregado");

        return "redirect:/inkamanu/admin/formularioCrearProducto";
    }

    @GetMapping("/admin/listaProductos")
    public String verListaDeProductos(Model model) {

        List<Producto> listaProductos = productoService.getAllProducts();
        model.addAttribute("listaProductos", listaProductos);

        return "listar_producto";
    }

    @GetMapping("/admin/editarProducto/{id}")
    public String editarProducto(@PathVariable(name = "id") int id, Model model) {
        Producto p = productoService.getProductById(id);

        if (p == null) {
            return "redirect:/inkamanu/admin/listaProductos";
        }

        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setDescripcion(p.getDescripcion());
        productoDTO.setGradoAlcohol(p.getGradoAlcohol());
        productoDTO.setId(p.getId());
        productoDTO.setNombre(p.getNombre());
        productoDTO.setPorcentajeDescuento(p.getPorcentajeDescuento());
        productoDTO.setPrecio(p.getPrecio());
        productoDTO.setStock(p.getStock());
        productoDTO.setTipoCerveza(p.getTipoCerveza());

        model.addAttribute("productoDTO", p);
        return "formularios/editar_producto";
    }

    @PostMapping("/admin/guardarProductoEditado")
    public String guardarProductoEditado(@Valid @ModelAttribute ProductoDTO productoDTO, BindingResult result,
            Model model, RedirectAttributes redirectAttrs) {

        if (result.hasErrors()) {
            return "formularios/registrar_producto";
        }

        productoService.updateProduct(productoDTO);
        redirectAttrs.addFlashAttribute("success", "Producto editado");
        return "redirect:/inkamanu/admin/editarProducto/" + productoDTO.getId();
    }

}
