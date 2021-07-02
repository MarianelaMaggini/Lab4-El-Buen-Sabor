package com.example.buensabor.controllers.articulos;

import com.example.buensabor.entities.articulos.HistoricoArticulo;
import com.example.buensabor.services.articulos.HistoricoArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:59787"})
@RestController
@RequestMapping("/historicoArticulos")//ruta principal
public class HistoricoArticuloController {

    private final HistoricoArticuloService historicoArticuloService;

    @Autowired
    public HistoricoArticuloController(HistoricoArticuloService historicoArticuloService) {
        this.historicoArticuloService = historicoArticuloService;
    }

    @GetMapping("/todos")
    public List<HistoricoArticulo> getHistoricoArticulos() {
        return historicoArticuloService.getHistoricoArticulos();
    }

    @GetMapping("/{id}")
    public Optional<HistoricoArticulo> getHistoricoArticuloById(@PathVariable("id") Long id) {
        return historicoArticuloService.getHistoricoArticuloById(id);
    }

    @GetMapping("/articulo")
    public List<HistoricoArticulo> getHistoricoArticuloByIdArticulo(@RequestParam("id") Long id) {
        return historicoArticuloService.getHistoricoArticuloByIdArticulo(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public HistoricoArticulo saveOrUpdateHistoricoArticulo(@RequestBody HistoricoArticulo historicoArticulo) {
        return historicoArticuloService.saveOrUpdateHistoricoArticulo(historicoArticulo);
    }
}

