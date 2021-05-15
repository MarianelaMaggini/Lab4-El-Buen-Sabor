package com.example.buensabor.controllers.cliente;

import com.example.buensabor.entities.cliente.UsuarioEntity;
import com.example.buensabor.services.cliente.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")//ruta principal
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/todos")
    public List<UsuarioEntity> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @GetMapping("/{id}")
    public Optional<UsuarioEntity> getUsuarioById(@PathVariable("id") Long id) {
        return usuarioService.getUsuarioById(id);
    }

    @PostMapping()
    public UsuarioEntity saveOrUpdateUsuario(@RequestBody UsuarioEntity usuario) {
        return usuarioService.saveOrUpdateUsuario(usuario);
    }
}
