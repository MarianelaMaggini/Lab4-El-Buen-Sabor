package com.example.buensabor.controllers.articulos;

import com.example.buensabor.entities.articulos.RecetaElaborado;
import com.example.buensabor.services.articulos.RecetaElaboradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recetasElaborado")//ruta principal
public class RecetaElaboradoController {

    private final RecetaElaboradoService recetaElaboradoService;

    @Autowired
    public RecetaElaboradoController(RecetaElaboradoService recetaElaboradoService) {
        this.recetaElaboradoService = recetaElaboradoService;
    }

    @GetMapping("/todos")
    public List<RecetaElaborado> getRecetas() {
        return recetaElaboradoService.getRecetas();
    }

    @GetMapping("/{id}")
    public Optional<RecetaElaborado> getRecetaById(@PathVariable("id") Long id) {
        return recetaElaboradoService.getRecetaById(id);
    }

    @GetMapping("/detalle")
    public List<RecetaElaborado> getRecetaByIdAed(@RequestParam("id") Long id) {
        return recetaElaboradoService.getRecetaByIdAed(id);
    }

    @GetMapping("/historica")
    public List<RecetaElaborado> getRecetaHistoricaByIdAed(@RequestParam("id") Long id) {
        return recetaElaboradoService.getRecetaHistoricaByIdAed(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public RecetaElaborado saveOrUpdateReceta(@RequestBody RecetaElaborado receta) {
        return recetaElaboradoService.saveOrUpdateReceta(receta);
    }
}
