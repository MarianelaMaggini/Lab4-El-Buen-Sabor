package com.example.buensabor.controllers.articulos;

import com.example.buensabor.entities.articulos.ArticuloElaboradoDetalleEntity;
import com.example.buensabor.services.articulos.ArticuloElaboradoDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/articulosElaboradosDetalles")//ruta principal
public class ArticuloElaboradoDetalleController {

    @Autowired
    ArticuloElaboradoDetalleService articuloElaboradoDetalleService;

    @GetMapping("/todos")
    public List<ArticuloElaboradoDetalleEntity> getArticuloDetalles() {
        return articuloElaboradoDetalleService.getArticuloDetalles();
    }

    @GetMapping("/{id}")
    public Optional<ArticuloElaboradoDetalleEntity> getArticuloDetalleById(@PathVariable("id") Long id) {
        return  articuloElaboradoDetalleService.getArticuloDetalleById(id);
    }

    @PostMapping()
    public ArticuloElaboradoDetalleEntity saveOrUpdateArticuloDetalle(@RequestBody ArticuloElaboradoDetalleEntity articuloDetalle) {
        return articuloElaboradoDetalleService.saveOrUpdateArticuloDetalle(articuloDetalle);
    }
}
