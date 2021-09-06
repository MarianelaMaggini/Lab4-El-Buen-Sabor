package com.example.buensabor.controllers.articulos;

import com.example.buensabor.entities.articulos.TipoArticulo;
import com.example.buensabor.services.articulos.TipoArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
@RestController
@RequestMapping("/tipoArticulos")//ruta principal
public class TipoArticuloController {

    private final TipoArticuloService tipoArticuloService;

    @Autowired
    public TipoArticuloController(TipoArticuloService tipoArticuloService) {
        this.tipoArticuloService = tipoArticuloService;
    }

    @GetMapping("/todos")
    public List<TipoArticulo> getTiposArticulo() {
        return tipoArticuloService.getTiposArticulo();
    }

    @GetMapping("/{id}")
    public Optional<TipoArticulo> getTipoArticuloById(@PathVariable("id") Long id) {
        return tipoArticuloService.getTipoArticuloById(id) ;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public TipoArticulo saveOrUpdateTipoArticulo(@RequestBody TipoArticulo tipoArticulo) {
        return tipoArticuloService.saveOrUpdateTipoArticulo(tipoArticulo);
    }
}
