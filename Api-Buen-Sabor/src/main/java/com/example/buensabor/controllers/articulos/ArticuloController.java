package com.example.buensabor.controllers.articulos;

import com.example.buensabor.entities.articulos.ArticuloEntity;
import com.example.buensabor.services.articulos.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/articulos")//ruta principal
public class ArticuloController {

    @Autowired
    ArticuloService articuloService;

    @GetMapping("/todos")
    public List<ArticuloEntity> getArticulos() {
        return articuloService.getArticulos();
    }

    @GetMapping("/{id}")
    public Optional<ArticuloEntity> getArticuloById(@PathVariable("id") Long id) { return articuloService.getArticuloById(id); }

    @GetMapping("/idRubro")
    public List<ArticuloEntity> getArticuloByIdRubro(@RequestParam("rubro") Long id) { return articuloService.getArticuloByIdRubro(id); }

    @PostMapping()
    public ArticuloEntity saveOrUpdateArticulo(@RequestBody ArticuloEntity articulo) { return articuloService.saveOrUpdateArticulo(articulo); }

    @DeleteMapping("/{id}")
    public String deleteArticuloById(@PathVariable("id") Long id) {
        boolean eliminado = articuloService.deleteArticuloById(id);
        if(eliminado) { return "Artículo eliminado!"; }
        else { return "No se puedo eliminar el artículo!"; }
    }
}
