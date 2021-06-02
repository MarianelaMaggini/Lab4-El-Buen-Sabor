package com.example.buensabor.controllers.cliente;

import com.example.buensabor.entities.cliente.Usuario;
import com.example.buensabor.services.cliente.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")//ruta principal
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/todos")
    public List<Usuario> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> getUsuarioById(@PathVariable("id") Long id) {
        return usuarioService.getUsuarioById(id);
    }

    @PostMapping()
    public Usuario saveOrUpdateUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveOrUpdateUsuario(usuario);
    }
}
