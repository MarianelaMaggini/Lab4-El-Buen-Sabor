package com.example.buensabor.services.cliente;

import com.example.buensabor.entities.cliente.UsuarioEntity;
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

    public List<UsuarioEntity> getUsuarios() {
        return (ArrayList<UsuarioEntity>) usuarioRepository.findAll();
    }

    public Optional<UsuarioEntity> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioEntity saveOrUpdateUsuario(UsuarioEntity usuario) {
        return (UsuarioEntity) usuarioRepository.save(usuario);
    }

    public UsuarioEntity updateUsuario(UsuarioEntity usuario) {
        return (UsuarioEntity) usuarioRepository.save(usuario);
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
