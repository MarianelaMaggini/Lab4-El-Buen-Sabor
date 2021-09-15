package com.example.buensabor.security.controllers;

import com.example.buensabor.security.entities.Usuario;
import com.example.buensabor.security.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cuenta")
public class CuentaController {
    private final UsuarioService usuarioService;

    @Autowired
    public CuentaController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/confirmar-cuenta")
    public String confirmarCuenta (@RequestParam("token") String token){
        Usuario usuario = usuarioService.findByTokenPassword(token).get();
        usuario.setEnabled(true);
        usuarioService.save(usuario);
        return "cuenta-verificada";
    }
}
