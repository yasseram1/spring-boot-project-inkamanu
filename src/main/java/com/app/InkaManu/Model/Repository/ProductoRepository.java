package com.app.InkaManu.Model.Repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.InkaManu.Model.Entity.Producto;

@Repository
@Qualifier("productoRepository")
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    
}
