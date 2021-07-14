package com.example.buensabor.controllers.articulos;

import com.example.buensabor.entities.articulos.UnidadMedida;
import com.example.buensabor.services.articulos.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:59787"})
@RestController
@RequestMapping("/unidadesMedida")//ruta principal
public class UnidadMedidaController {

    private final UnidadMedidaService unidadMedidaService;

    @Autowired
    public UnidadMedidaController(UnidadMedidaService unidadMedidaService) {
        this.unidadMedidaService = unidadMedidaService;
    }

    @GetMapping("/todos")
    public List<UnidadMedida> getUnidades() {
        return unidadMedidaService.getUnidades();
    }

    @GetMapping("/{id}")
    public Optional<UnidadMedida> getUnidadById(@PathVariable("id") Long id) {
        return unidadMedidaService.getUnidadById(id);
    }

    @GetMapping("/articulo")
    public Optional<UnidadMedida> getUnidadMedidaByIdArticulo(@RequestParam("id") Long id) {
        return unidadMedidaService.getUnidadMedidaByIdArticulo(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public UnidadMedida saveOrUpdateUnidad(@RequestBody UnidadMedida unidad) {
        return unidadMedidaService.saveOrUpdateUnidad(unidad);
    }

}
