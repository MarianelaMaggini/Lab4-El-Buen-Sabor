package com.example.buensabor.controllers.domicilio;

import com.example.buensabor.entities.domicilio.Domicilio;
import com.example.buensabor.entities.domicilio.Localidad;
import com.example.buensabor.services.domicilio.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/localidades")//ruta principal
public class LocalidadController {

    @Autowired
    LocalidadService localidadService;

    @GetMapping("/todos")
    public List<Localidad> getLocalidades() {
        return localidadService.getLocalidades();
    }

    @GetMapping("/{id}")
    public Optional<Localidad> getLocalidadById(@PathVariable("id") Long id) {
        return localidadService.getLocalidadById(id);
    }

    @PostMapping()
    public Localidad saveOrUpdateDomicilio(@RequestBody Localidad localidad) {
        return localidadService.saveOrUpdateLocalidad(localidad);
    }
}
