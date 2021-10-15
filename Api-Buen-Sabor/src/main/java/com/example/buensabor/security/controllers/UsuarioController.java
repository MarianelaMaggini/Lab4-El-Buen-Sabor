package com.example.buensabor.security.controllers;

import com.example.buensabor.dtos.Message;
import com.example.buensabor.security.dto.*;
import com.example.buensabor.security.entities.Rol;
import com.example.buensabor.security.entities.Usuario;
import com.example.buensabor.security.enums.RolNombre;
import com.example.buensabor.security.jwt.JwtProvider;
import com.example.buensabor.security.services.EnviarMailService;
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
import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class UsuarioController {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final RolService rolService;
    private final JwtProvider jwtProvider;
    private final EnviarMailService emailSenderService;
    @Value("${google.clientId}")
    private String googleClientId;
    @Value("${secretPsw}")
    private String secretPsw;
    @Value("${spring.mail.username}")
    private String mailFrom;
    private static final String SUBJECT = "Bienvenido, solo queda verificar tu correo.";
    @Value("${mail.urlVerification}")
    private String urlVerification;

    @Autowired
    public UsuarioController(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UsuarioService usuarioService, RolService rolService, JwtProvider jwtProvider, EnviarMailService emailSenderService) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        this.jwtProvider = jwtProvider;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/update")
    public Usuario update(@RequestBody Usuario usuario){
        usuario.setEnabled(true);
        return usuarioService.save(usuario);
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
            NuevoUsuario nuevoUsuario = new NuevoUsuario();
            nuevoUsuario.setNombre((String) payload.get("given_name"));
            nuevoUsuario.setApellido((String) payload.get("family_name"));
            nuevoUsuario.setTelefono("");
            nuevoUsuario.setEmail(payload.getEmail());
            nuevoUsuario.setClave(secretPsw);
            nuevoUsuario.setEnabled(true);
            usuario = saveNewUsuario(nuevoUsuario);
        }
        JwtDto jwtDto = loginUsuario(usuario.getEmail(), secretPsw);
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) throws MessagingException {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Message("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        }
        if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
            return new ResponseEntity<>(new Message("ese email ya existe"), HttpStatus.BAD_REQUEST);
        }
        UUID uuid = UUID.randomUUID();
        String tokenPassword = uuid.toString();
        nuevoUsuario.setEnabled(false);
        nuevoUsuario.setTokenPassword(tokenPassword);
        saveNewUsuario(nuevoUsuario);
        EmailValuesDto emailValuesDto = new EmailValuesDto();
        emailValuesDto.setNombre(nuevoUsuario.getNombre());
        emailValuesDto.setMailFrom(mailFrom);
        emailValuesDto.setMailTo(nuevoUsuario.getEmail());
        emailValuesDto.setSubject(SUBJECT);

        emailValuesDto.setTokenPassword(tokenPassword);
        emailSenderService.sendEmail(emailValuesDto, "email-verification", urlVerification);
        return new ResponseEntity<>(new Message("Usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        Usuario usuario = usuarioService.getByEmail(loginUsuario.getEmail()).get();
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Message("Campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        }
        if (!usuario.isEnabled()){
            return new ResponseEntity(new Message("Cuenta no verificada"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(loginUsuario(loginUsuario.getEmail(), loginUsuario.getClave()), HttpStatus.OK);
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



    private JwtDto loginUsuario(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generarToken(authentication);
        return new JwtDto(jwt);
    }

    private Usuario saveNewUsuario(NuevoUsuario nuevoUsuario) {
        Usuario usuario = new Usuario(
                nuevoUsuario.getNombre(),
                nuevoUsuario.getApellido(),
                nuevoUsuario.getTelefono(),
                nuevoUsuario.getEmail(),
                passwordEncoder.encode(nuevoUsuario.getClave()),
                nuevoUsuario.isEnabled(),
                nuevoUsuario.getTokenPassword());
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if (nuevoUsuario.getRoles().contains("admin")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        if (nuevoUsuario.getRoles().contains("cocinero")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_COCINERO).get());
        }
        if (nuevoUsuario.getRoles().contains("cajero")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_CAJERO).get());
        }
        usuario.setRoles(roles);
        return usuarioService.save(usuario);
    }
}
