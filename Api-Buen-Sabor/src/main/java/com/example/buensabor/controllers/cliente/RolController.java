package com.example.buensabor.controllers.cliente;

import com.example.buensabor.entities.cliente.Rol;
import com.example.buensabor.services.cliente.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/roles")//ruta principal
public class RolController {

    @Autowired
    RolService rolService;

    @GetMapping("/todos")
    public List<Rol> getRoles() {
        return rolService.getRoles();
    }

    @GetMapping("/{id}")
    public Optional<Rol> getRolById(@PathVariable("id") Long id) {
        return rolService.getRolById(id);
    }

    @PostMapping()
    public Rol saveOrUpdateRol(@RequestBody Rol rol) {
        return rolService.saveOrUpdateRol(rol);
    }
}
