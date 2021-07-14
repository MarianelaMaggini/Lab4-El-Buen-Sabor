package com.example.buensabor.controllers.articulos;

import com.example.buensabor.entities.articulos.Inventario;
import com.example.buensabor.services.articulos.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:59787"})
@RestController
@RequestMapping("/inventarios")//ruta principal
public class InventarioController {

    private final InventarioService inventarioService;
    @Autowired
    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping("/todos")
    public List<Inventario> getInventarioCompleto() {
        return inventarioService.getInventarioCompleto();
    }

    @GetMapping("/{id}")
    public Optional<Inventario> getInventarioById(@PathVariable("id") Long id) {
        return inventarioService.getInventarioById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public Inventario saveOrUpdateInventario(@RequestBody Inventario inventario) {
        return inventarioService.saveOrUpdateInventario(inventario);
    }

}
