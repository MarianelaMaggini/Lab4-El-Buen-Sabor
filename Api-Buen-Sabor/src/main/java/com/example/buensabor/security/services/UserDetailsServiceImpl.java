package com.example.buensabor.security.services;

import com.example.buensabor.security.entities.Usuario;
import com.example.buensabor.security.entities.UsuarioPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Autowired
    public UserDetailsServiceImpl(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getByEmail(email).get();
        return UsuarioPrincipal.build(usuario);
    }
}
