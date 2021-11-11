package com.example.buensabor.security.controllers;

import com.example.buensabor.dtos.Message;
import com.example.buensabor.security.dto.ChangePasswordDto;
import com.example.buensabor.security.dto.EmailValuesDto;
import com.example.buensabor.security.entities.Usuario;
import com.example.buensabor.security.services.EnviarMailService;
import com.example.buensabor.security.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final UsuarioService usuarioService;
    private final EnviarMailService enviarMailService;
    private final PasswordEncoder passwordEncoder;
    private static final String SUBJECT = "Cambio de contraseña.";
    @Value("${spring.mail.username}")
    private String mailFrom;
    @Value("${mail.urlPassword}")
    private String urlPassword;

    @Autowired
    public AccountController(UsuarioService usuarioService, EnviarMailService enviarMailService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.enviarMailService = enviarMailService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/confirm-account")
    public String confirmAccount (@RequestParam("token") String token){
        Usuario usuario = usuarioService.getByTokenPassword(token).get();
        usuario.setEnabled(true);
        usuario.setTokenPassword(null);
        usuarioService.save(usuario);
        return "cuenta-verificada";
    }

    @PostMapping("/send-email-password")
    public ResponseEntity<?> sendEmailForgotPassword(@RequestBody EmailValuesDto emailValuesDto) throws MessagingException {
        Optional<Usuario> usuarioOptional = usuarioService.getByEmail(emailValuesDto.getMailTo());
        if (!usuarioOptional.isPresent())
            return new ResponseEntity<>(new Message("No existe ningún usuario con ese email"), HttpStatus.NOT_FOUND);
        Usuario usuario = usuarioOptional.get();
        emailValuesDto.setNombre(usuario.getNombre());
        emailValuesDto.setMailFrom(mailFrom);
        emailValuesDto.setMailTo(usuario.getEmail());
        emailValuesDto.setSubject(SUBJECT);
        UUID uuid = UUID.randomUUID();
        String tokenPassword = uuid.toString();
        emailValuesDto.setTokenPassword(tokenPassword);
        usuario.setTokenPassword(tokenPassword);
        usuarioService.save(usuario);
        enviarMailService.sendEmail(emailValuesDto, "email-password", urlPassword);
        return new ResponseEntity<>(new Message("Te enviamos un correo para cambiar contraseña"), HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto dto, BindingResult result){
        if (result.hasErrors())
            return new ResponseEntity<>(new Message("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        if (!dto.getPassword().equals(dto.getConfirmPassword()))
            return new ResponseEntity<>(new Message("Las contraseñas no coinciden"), HttpStatus.BAD_REQUEST);

        Optional<Usuario> usuarioOptional = usuarioService.getByTokenPassword(dto.getTokenPassword());
        if (!usuarioOptional.isPresent())
            return new ResponseEntity<>(new Message("No existe ningún usuario con esas credenciales"), HttpStatus.BAD_REQUEST);
        Usuario usuario = usuarioOptional.get();
        String newPassword = passwordEncoder.encode(dto.getPassword());
        usuario.setClave(newPassword);
        usuario.setTokenPassword(null);
        usuarioService.save(usuario);
        return new ResponseEntity<>(new Message("Se ha modificado su contraseña"), HttpStatus.OK);
    }
}
