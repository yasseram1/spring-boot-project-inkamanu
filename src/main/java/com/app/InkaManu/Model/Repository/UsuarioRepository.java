package com.app.InkaManu.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.InkaManu.Model.Entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
}
