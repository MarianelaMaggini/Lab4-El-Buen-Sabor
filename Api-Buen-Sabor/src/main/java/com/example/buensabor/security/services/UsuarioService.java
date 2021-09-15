package com.example.buensabor.security.services;

import com.example.buensabor.security.entities.Usuario;
import com.example.buensabor.security.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public Optional<Usuario> getByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }
    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
    public Optional<Usuario> findByTokenPassword(String tokenPassword){
        return usuarioRepository.findByTokenPassword(tokenPassword);
    }
    public Usuario save(Usuario usuario){
        return  usuarioRepository.save(usuario);
    }
}
