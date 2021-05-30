package com.example.buensabor.controllers.rubro;

import com.example.buensabor.entities.rubro.RubroEntity;
import com.example.buensabor.services.rubro.RubroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/rubros") // Ruta principal
public class RubroController {

    @Autowired
    RubroService rubroService;

    @GetMapping("/todos")
    public List<RubroEntity> getRubros() {
        return rubroService.getRubros() ;
    }

    @GetMapping("/{id}")
    public Optional<RubroEntity> getRubroById(@PathVariable("id") Long id) {
        return rubroService.getRubroById(id);
    }

    @PostMapping()
    public RubroEntity saveOrUpdateRubro(@RequestBody RubroEntity rubro) { return rubroService.saveOrUpdateRubro(rubro); }

    @DeleteMapping("/{id}")
    public String deleteRubroById(@PathVariable("id") Long id) {
        boolean eliminado = rubroService.deleteRubroById(id);
        if(eliminado) { return "Rubro eliminado!"; }
        else { return "No se puedo eliminar el rubro!"; }
    }
}
