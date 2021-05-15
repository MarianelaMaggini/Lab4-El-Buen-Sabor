package com.example.buensabor.controllers.articulos;

import com.example.buensabor.entities.articulos.ArticuloEntity;
import com.example.buensabor.services.articulos.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/articulos")//ruta principal
public class ArticuloController {

    @Autowired
    ArticuloService articuloService;

    @GetMapping()
    public List<ArticuloEntity> getArticulos() {
        return articuloService.getArticulos();
    }

    @GetMapping("/{id}")
    public Optional<ArticuloEntity> getArticuloById(@PathVariable("id") Long id) {
        return articuloService.getArticuloById(id);
    }

    @PostMapping()
    public ArticuloEntity saveOrUpdateArticulo(@RequestBody ArticuloEntity articulo) {
        return articuloService.saveOrUpdateArticulo(articulo);
    }
}
