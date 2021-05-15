package com.example.buensabor.controllers.articulos;

import com.example.buensabor.entities.articulos.RecetaElaboradoEntity;
import com.example.buensabor.services.articulos.RecetaElaboradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/recetasElaborado")//ruta principal
public class RecetaElaboradoController {

    @Autowired
    RecetaElaboradoService recetaElaboradoService;

    @GetMapping("/todos")
    public List<RecetaElaboradoEntity> getRecetas() {
        return recetaElaboradoService.getRecetas();
    }

    @GetMapping("/{id}")
    public Optional<RecetaElaboradoEntity> getRecetaById(@PathVariable("id") Long id) {
        return recetaElaboradoService.getRecetaById(id);
    }

    @PostMapping()
    public RecetaElaboradoEntity saveOrUpdateReceta(@RequestBody RecetaElaboradoEntity receta) {
        return recetaElaboradoService.saveOrUpdateReceta(receta);
    }
}
