package com.example.buensabor.security.controllers;

import com.example.buensabor.security.dto.JwtDto;
import com.example.buensabor.security.dto.LoginUsuario;
import com.example.buensabor.security.dto.NuevoUsuario;
import com.example.buensabor.security.entities.Rol;
import com.example.buensabor.security.entities.Usuario;
import com.example.buensabor.security.enums.RolNombre;
import com.example.buensabor.security.jwt.JwtProvider;
import com.example.buensabor.security.services.RolService;
import com.example.buensabor.security.services.UsuarioService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;
    UsuarioService usuarioService;
    RolService rolService;
    JwtProvider jwtProvider;
    @Value("${google.clientId}")
    String googleClientId;
    @Value("${secretPsw}")
    String secretPsw;

    @Autowired
    public UsuarioController(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UsuarioService usuarioService, RolService rolService, JwtProvider jwtProvider) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody JwtDto token) throws IOException {
        final NetHttpTransport transport = new NetHttpTransport();
        final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder verifier = new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
                .setAudience(Collections.singletonList(googleClientId));
        final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), token.getToken());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();
        Usuario usuario;
        if (usuarioService.existsByEmail(payload.getEmail())) {
            usuario = usuarioService.getByEmail(payload.getEmail()).get();
        } else {
            usuario = saveUsuarioGoogle((String) payload.get("given_name"), (String) payload.get("family_name"), "", payload.getEmail());
        }
        JwtDto jwtDto = loginUsuarioGoogle(usuario);
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("campos mal puestos o email inválido", HttpStatus.BAD_REQUEST);
        }
        if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
            return new ResponseEntity<>("ese email ya existe", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario(
                nuevoUsuario.getNombre(),
                nuevoUsuario.getApellido(),
                nuevoUsuario.getTelefono(),
                nuevoUsuario.getEmail(),
                passwordEncoder.encode(nuevoUsuario.getClave()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if (nuevoUsuario.getRoles().contains("admin")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity<>("Usuario guardado", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity("campos mal puestos o email inválido", HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getEmail(), loginUsuario.getClave()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generarToken(authentication);
        JwtDto jwtDto = new JwtDto(jwt);
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refreshToken(@RequestBody JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDto jwt = new JwtDto(token);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public Optional<Usuario> getUsuario(@PathVariable("email") String email) {
        return usuarioService.getByEmail(email);
    }

    private JwtDto loginUsuarioGoogle(Usuario usuario) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), secretPsw));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generarToken(authentication);
        JwtDto jwtDto = new JwtDto(jwt);
        return jwtDto;
    }

    private Usuario saveUsuarioGoogle(String nombre, String apellido, String telefono, String email) {
        Usuario usuario = new Usuario(nombre, apellido, telefono, email, passwordEncoder.encode(secretPsw));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        usuario.setRoles(roles);
        return usuarioService.save(usuario);
    }

}
