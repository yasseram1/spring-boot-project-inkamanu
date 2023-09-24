package com.app.InkaManu.Model.Service;

import java.util.List;

import com.app.InkaManu.Model.DTO.UsuarioDTO;
import com.app.InkaManu.Model.Entity.Usuario;

public interface UsuarioService {
    Usuario createUser(UsuarioDTO usuario);

    Usuario getUserById(int usuarioId);

    List<Usuario> getAllUsers();

    void updateUser(Usuario usuario);

    void deleteUser(int usuarioId);
}
