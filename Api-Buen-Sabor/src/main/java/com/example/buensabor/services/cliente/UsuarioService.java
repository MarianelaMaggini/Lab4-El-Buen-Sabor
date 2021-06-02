package com.example.buensabor.services.cliente;

import com.example.buensabor.entities.cliente.Usuario;
import com.example.buensabor.repositories.cliente.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarios() {
        return (ArrayList<Usuario>) usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario saveOrUpdateUsuario(Usuario usuario) {
        return (Usuario) usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Usuario usuario) {
        return (Usuario) usuarioRepository.save(usuario);
    }

    public boolean deleteUsuarioById(Long id) {
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
