package com.example.buensabor.security.controllers;

import com.example.buensabor.dtos.Message;
import com.example.buensabor.security.dto.*;
import com.example.buensabor.security.entities.Usuario;
import com.example.buensabor.security.services.EnviarMailService;
import com.example.buensabor.security.services.UsuarioService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final EnviarMailService emailSenderService;
    @Value("${google.clientId}")
    private String googleClientId;
    @Value("${secretPsw}")
    private String secretPsw;
    @Value("${spring.mail.username}")
    private String mailFrom;
    private static final String SUBJECT = "Bienvenido, solo queda verificar tu correo.";
    @Value("${mail.url.verification}")
    private String urlVerification;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, EnviarMailService emailSenderService) {
        this.usuarioService = usuarioService;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/update")
    public Usuario update(@RequestBody Usuario usuario){
        usuario.setEnabled(true);
        return usuarioService.update(usuario);
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
            usuario = usuarioService.getByEmail(payload.getEmail()).orElseThrow();
        } else {
            NuevoUsuarioDto nuevoUsuarioDto = new NuevoUsuarioDto();
            nuevoUsuarioDto.setNombre((String) payload.get("given_name"));
            nuevoUsuarioDto.setApellido((String) payload.get("family_name"));
            nuevoUsuarioDto.setTelefono("");
            nuevoUsuarioDto.setEmail(payload.getEmail());
            nuevoUsuarioDto.setClave(secretPsw);
            nuevoUsuarioDto.setEnabled(true);
            usuario = usuarioService.save(nuevoUsuarioDto);
        }
        LoginUsuarioDto loginUsuarioDto = new LoginUsuarioDto(usuario.getEmail(), secretPsw);
        JwtDto jwtDto = usuarioService.login(loginUsuarioDto);
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuarioDto nuevoUsuarioDto, BindingResult bindingResult) throws MessagingException {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Message("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        }
        if (usuarioService.existsByEmail(nuevoUsuarioDto.getEmail())) {
            return new ResponseEntity<>(new Message("ese email ya existe"), HttpStatus.BAD_REQUEST);
        }
        UUID uuid = UUID.randomUUID();
        String tokenPassword = uuid.toString();
        nuevoUsuarioDto.setEnabled(false);
        nuevoUsuarioDto.setTokenPassword(tokenPassword);
        usuarioService.save(nuevoUsuarioDto);
        EmailValuesDto emailValuesDto = new EmailValuesDto();
        emailValuesDto.setNombre(nuevoUsuarioDto.getNombre());
        emailValuesDto.setMailFrom(mailFrom);
        emailValuesDto.setMailTo(nuevoUsuarioDto.getEmail());
        emailValuesDto.setSubject(SUBJECT);

        emailValuesDto.setTokenPassword(tokenPassword);
        emailSenderService.sendEmail(emailValuesDto, "email-verification", urlVerification);
        return new ResponseEntity<>(new Message("Usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsuarioDto loginUsuarioDto, BindingResult bindingResult) {
        Usuario usuario = usuarioService.getByEmail(loginUsuarioDto.getEmail()).orElseThrow();
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Message("Campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        }
        if (!usuario.isEnabled()){
            return new ResponseEntity<>(new Message("Cuenta no verificada"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(usuarioService.login(loginUsuarioDto), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody JwtDto jwtDto) throws ParseException {
        return new ResponseEntity<>(usuarioService.refreshToken(jwtDto), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public Optional<Usuario> getUsuario(@PathVariable("email") String email) {
        return usuarioService.getByEmail(email);
    }

}
