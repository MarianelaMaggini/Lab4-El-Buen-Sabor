package com.example.buensabor.controllers.articulos;

import com.example.buensabor.entities.articulos.UnidadMedidaEntity;
import com.example.buensabor.services.articulos.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/unidadesMedida")//ruta principal
public class UnidadMedidaController {

    private final UnidadMedidaService unidadMedidaService;

    @Autowired
    public UnidadMedidaController(UnidadMedidaService unidadMedidaService) {
        this.unidadMedidaService = unidadMedidaService;
    }

    @GetMapping("/todos")
    public List<UnidadMedidaEntity> getUnidades() {
        return unidadMedidaService.getUnidades();
    }

    @GetMapping("/{id}")
    public Optional<UnidadMedidaEntity> getUnidadById(@PathVariable("id") Long id) {
        return unidadMedidaService.getUnidadById(id);
    }

    @PostMapping()
    public UnidadMedidaEntity saveOrUpdateUnidad(@RequestBody UnidadMedidaEntity unidad) {
        return unidadMedidaService.saveOrUpdateUnidad(unidad);
    }
}
