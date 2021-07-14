package com.example.buensabor.controllers.rubro;

import com.example.buensabor.entities.rubro.Rubro;
import com.example.buensabor.services.rubro.RubroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:59787"})
@RestController
@RequestMapping("/rubros") // Ruta principal
public class RubroController {

    @Autowired
    RubroService rubroService;

    @GetMapping("/todosActivo")
    public List<Rubro> getRubrosSinFechaDeBaja() {
        return rubroService.getRubrosSinFechaDeBaja() ;
    }

    @GetMapping("/todos")
    public List<Rubro> getRubros() {
        return rubroService.getRubros() ;
    }

    @GetMapping("/{id}")
    public Optional<Rubro> getRubroById(@PathVariable("id") Long id) {
        return rubroService.getRubroById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public Rubro saveOrUpdateRubro(@RequestBody Rubro rubro) { return rubroService.saveOrUpdateRubro(rubro); }

}
