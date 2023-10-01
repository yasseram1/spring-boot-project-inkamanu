package com.app.InkaManu.Model.ServiceImpl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.InkaManu.Model.DTO.ProductoDTO;
import com.app.InkaManu.Model.Entity.Producto;
import com.app.InkaManu.Model.Repository.ProductoRepository;
import com.app.InkaManu.Model.Service.ProductoService;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.StorageClient;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(@Qualifier("productoRepository") ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto saveProduct(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto getProductById(int producotId) {

        Optional<Producto> productoOptional = productoRepository.findById(producotId);
        return productoOptional.orElse(null);

    }

    @Override
    public List<Producto> getAllProducts() {
        return productoRepository.findAll();
    }

    @Override
    public void updateProduct(ProductoDTO producto) {
        Producto p = productoRepository.findById(producto.getId()).orElse(null);
        p.setNombre(producto.getNombre());
        p.setDescripcion(producto.getDescripcion());
        MultipartFile imagen = producto.getImagen();
        if (!imagen.isEmpty()) {
            try {
                // Subir Archivo (Imagen)
                Storage storage = StorageClient.getInstance().bucket().getStorage(); // Obtengo la instancia del bucket
                                                                                     // con
                                                                                     // el storage que estoy trabajando
                // MultipartFile imagen = productoDTO.getImagen(); // Archivo que se subira a
                // Firebase Storage
                String bucketName = "inkamanu-springboot.appspot.com"; // Nombre del bucket en el que se almacenara el
                                                                       // archivo
                String filePath = "Imagenes/" + imagen.getOriginalFilename(); // Path de a donde queremos que se suba
                                                                              // laimagen (dentro del bucket)
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
        p.setPrecio(producto.getPrecio());
        p.setStock(producto.getStock());
        p.setFechaActualizacion(LocalDateTime.now());
        p.setGradoAlcohol(producto.getGradoAlcohol());
        p.setTipoCerveza(producto.getTipoCerveza());
        p.setPorcentajeDescuento(producto.getPorcentajeDescuento());

        productoRepository.save(p);
    }

    @Override
    public void deleteProduct(int productoId) {
        productoRepository.deleteById(productoId);
    }

}
