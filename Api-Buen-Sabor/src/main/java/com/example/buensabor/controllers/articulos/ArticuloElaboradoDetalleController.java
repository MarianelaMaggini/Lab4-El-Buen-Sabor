package com.example.buensabor.controllers.articulos;

import com.example.buensabor.entities.articulos.ArticuloElaboradoDetalle;
import com.example.buensabor.services.articulos.ArticuloElaboradoDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/articulosElaboradosDetalles")//ruta principal
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

    @PostMapping()
    public ArticuloElaboradoDetalle saveOrUpdateArticuloDetalle(@RequestBody ArticuloElaboradoDetalle articuloDetalle) {
        return articuloElaboradoDetalleService.saveOrUpdateArticuloDetalle(articuloDetalle);
    }
}
