package com.example.buensabor.controllers.articulos;

import com.example.buensabor.entities.articulos.Articulo;
import com.example.buensabor.services.articulos.ArticuloService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/articulos") // Ruta principal
public class ArticuloController {

    @Autowired
    ArticuloService articuloService;

    @ApiOperation("Muestra un listado de artículos que no tengan fecha de baja")
    @GetMapping("/todosActivos")
    public List<Articulo> getArticulosSinFechaDeBaja() {
        return articuloService.getArticulosSinFechaDeBaja();
    }

    @ApiOperation("Muestra un listado de artículos")
    @GetMapping("/todos")
    public List<Articulo> getArticulos() {
        return articuloService.getArticulos();
    }

    @GetMapping("/{id}")
    public Optional<Articulo> getArticuloById(@PathVariable("id") Long id) {
        return articuloService.getArticuloById(id);
    }

    @GetMapping("/idRubro")
    public List<Articulo> getArticuloByIdRubro(@RequestParam("rubro") Long id) {
        return articuloService.getArticuloByIdRubro(id);
    }

    @GetMapping("/idTipoArticulo")
    public List<Articulo> getArticuloByIdTipoArticulo(@RequestParam("id") Long id) {
        return articuloService.getArticuloByIdTipoArticulo(id);
    }

    @GetMapping("/idTipoArticuloWithPrice")
    public List<Articulo> getArticuloByIdTipoArticuloWithPrice(@RequestParam("id") Long id) {
        return articuloService.getArticuloByIdTipoArticuloWithPrice(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public Articulo saveOrUpdateArticulo(@RequestBody Articulo articulo) {
        return articuloService.saveOrUpdateArticulo(articulo);
    }

}
