package com.example.buensabor.controllers.articulos;

import com.example.buensabor.entities.articulos.TipoArticuloEntity;
import com.example.buensabor.services.articulos.TipoArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tipoArticulos")//ruta principal
public class TipoArticuloController {

    @Autowired
    TipoArticuloService tipoArticuloService;

    @GetMapping("/todos")
    public List<TipoArticuloEntity> getTiposArticulo() {
        return tipoArticuloService.getTiposArticulo();
    }

    @GetMapping("/{id}")
    public Optional<TipoArticuloEntity> getTipoArticuloById(@PathVariable("id") Long id) {
        return tipoArticuloService.getTipoArticuloById(id) ;
    }

    @PostMapping()
    public TipoArticuloEntity saveOrUpdateTipoArticulo(@RequestBody TipoArticuloEntity tipoArticulo) {
        return tipoArticuloService.saveOrUpdateTipoArticulo(tipoArticulo);
    }
}
