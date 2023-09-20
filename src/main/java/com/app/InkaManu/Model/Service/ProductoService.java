package com.app.InkaManu.Model.Service;

import java.util.List;

import com.app.InkaManu.Model.Entity.Producto;

public interface ProductoService {
    Producto createProduct(Producto producto);

    Producto getProductById(int producotId);

    List<Producto> getAllProducts();

    void updateProduct(Producto producto);

    void deleteProduct(int productoId);
}
