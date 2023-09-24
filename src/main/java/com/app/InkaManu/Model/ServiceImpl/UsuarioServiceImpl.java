package com.app.InkaManu.Model.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.InkaManu.Model.DTO.UsuarioDTO;
import com.app.InkaManu.Model.Entity.Usuario;
import com.app.InkaManu.Model.Repository.UsuarioRepository;
import com.app.InkaManu.Model.Service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(@Autowired UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario createUser(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setApellidos(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setNumTelefonico(usuarioDTO.getNumTelefonico());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setRol("Cliente");
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario getUserById(int usuarioId) {
        return usuarioRepository.findById(usuarioId).orElse(null);
    }

    @Override
    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    @Override
    public void updateUser(Usuario usuario) {
        Usuario u = usuarioRepository.findById(usuario.getId()).orElse(null);
        u.setApellidos(usuario.getApellidos());
        u.setEmail(usuario.getEmail());
        u.setNombre(usuario.getNombre());
        u.setNumTelefonico(usuario.getNumTelefonico());
        u.setPassword(usuario.getPassword());
        usuarioRepository.save(u);
    }

    @Override
    public void deleteUser(int usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }
    
}
