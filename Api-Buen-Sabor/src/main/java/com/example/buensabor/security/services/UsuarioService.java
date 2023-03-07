package com.example.buensabor.security.services;

import com.example.buensabor.security.dto.JwtDto;
import com.example.buensabor.security.dto.LoginUsuarioDto;
import com.example.buensabor.security.dto.NuevoUsuarioDto;
import com.example.buensabor.security.entities.Rol;
import com.example.buensabor.security.entities.Usuario;
import com.example.buensabor.security.enums.RolNombre;
import com.example.buensabor.security.jwt.JwtProvider;
import com.example.buensabor.security.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolService rolService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;


    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, RolService rolService, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolService = rolService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    public Optional<Usuario> getByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Optional<Usuario> getByTokenPassword(String tokenPassword){
        return usuarioRepository.findByTokenPassword(tokenPassword);
    }
    public Usuario save(NuevoUsuarioDto nuevoUsuarioDto){
        Usuario usuario = new Usuario(
                nuevoUsuarioDto.getNombre(),
                nuevoUsuarioDto.getApellido(),
                nuevoUsuarioDto.getTelefono(),
                nuevoUsuarioDto.getEmail(),
                passwordEncoder.encode(nuevoUsuarioDto.getClave()),
                nuevoUsuarioDto.isEnabled(),
                nuevoUsuarioDto.getTokenPassword());
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).orElseThrow());
        if (nuevoUsuarioDto.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).orElseThrow());
        usuario.setRoles(roles);
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public JwtDto login (LoginUsuarioDto loginUsuarioDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuarioDto.getEmail(), loginUsuarioDto.getClave()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generarToken(authentication);
        return new JwtDto(jwt);
    }

    public JwtDto refreshToken(JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        return new JwtDto(token);
    }
}
