package com.app.InkaManu.Model.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.app.InkaManu.Model.Entity.Producto;
import com.app.InkaManu.Model.Repository.ProductoRepository;
import com.app.InkaManu.Model.Service.ProductoService;

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
    public void updateProduct(Producto producto) {
        Producto p = productoRepository.findById(producto.getId()).orElse(null);
        p.setNombre(producto.getNombre());
        p.setDescripcion(producto.getDescripcion());
        p.setImagen(producto.getImagen());
        p.setPrecio(producto.getPrecio());
        p.setStock(producto.getStock());
        p.setFechaActualizacion(LocalDateTime.now());

        productoRepository.save(producto);
    }

    @Override
    public void deleteProduct(int productoId) {
        productoRepository.deleteById(productoId);
    }

}
