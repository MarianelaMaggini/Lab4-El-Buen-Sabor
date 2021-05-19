package com.example.buensabor.controllers.rubro;

import com.example.buensabor.entities.rubro.RubroEntity;
import com.example.buensabor.services.rubro.RubroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rubros")//ruta principal
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
    public RubroEntity saveOrUpdateRubro(@RequestBody RubroEntity rubro) {
        return rubroService.saveOrUpdateRubro(rubro);
    }
}