package com.example.buensabor.controllers.articulos;

import com.example.buensabor.entities.articulos.ArticuloElaboradoDetalle;
import com.example.buensabor.services.articulos.ArticuloElaboradoDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:59787"})
@RestController
@RequestMapping("/articuloElaboradoDetalles")//ruta principal
public class ArticuloElaboradoDetalleController {

    private final ArticuloElaboradoDetalleService articuloElaboradoDetalleService;

    @Autowired
    public ArticuloElaboradoDetalleController(ArticuloElaboradoDetalleService articuloElaboradoDetalleService) {
        this.articuloElaboradoDetalleService = articuloElaboradoDetalleService;
    }

    @GetMapping("/todos")
    public List<ArticuloElaboradoDetalle> getArticuloDetalles() {
        return articuloElaboradoDetalleService.getArticuloDetalles();
    }

    @GetMapping("/{id}")
    public Optional<ArticuloElaboradoDetalle> getArticuloDetalleById(@PathVariable("id") Long id) {
        return articuloElaboradoDetalleService.getArticuloDetalleById(id);
    }

    @GetMapping("/articulo")
    public Optional<ArticuloElaboradoDetalle> getArticuloDetalleByIdArticulo(@RequestParam("id") Long id) {
        return articuloElaboradoDetalleService.getArticuloDetalleByIdArticulo(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ArticuloElaboradoDetalle saveOrUpdateArticuloDetalle(@RequestBody ArticuloElaboradoDetalle articuloDetalle) {
        return articuloElaboradoDetalleService.saveOrUpdateArticuloDetalle(articuloDetalle);
    }
}
