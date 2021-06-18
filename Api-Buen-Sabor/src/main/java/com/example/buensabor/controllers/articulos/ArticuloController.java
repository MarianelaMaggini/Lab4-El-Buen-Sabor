package com.example.buensabor.controllers.articulos;

import com.example.buensabor.entities.articulos.Articulo;
import com.example.buensabor.services.articulos.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:59787"})
@RestController
@RequestMapping("/articulos")//ruta principal
public class ArticuloController {

    @Autowired
    ArticuloService articuloService;

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

    @GetMapping("/idTiposArticulos")
    public List<Articulo> getArticuloByIdTipo(@RequestParam("tipoUno") Long idUno, @RequestParam("tipoDos") Long idDos) {
        return articuloService.getArticuloByIdTipoArticuloOrIdTipoArticulo(idUno, idDos);
    }

    @GetMapping("/idTipoArticulo")
    public List<Articulo> getArticuloByIdTipoArticulo(@RequestParam("id") Long id) {
        return articuloService.getArticuloByIdTipoArticulo(id);
    }

    @PostMapping()
    public Articulo saveOrUpdateArticulo(@RequestBody Articulo articulo) {
        return articuloService.saveOrUpdateArticulo(articulo);
    }

    @DeleteMapping("/{id}")
    public String deleteArticuloById(@PathVariable("id") Long id) {
        boolean eliminado = articuloService.deleteArticuloById(id);
        if (eliminado) {
            return "Artículo eliminado!";
        } else {
            return "No se puedo eliminar el artículo!";
        }
    }
}
