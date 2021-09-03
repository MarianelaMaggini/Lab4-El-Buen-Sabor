package com.example.buensabor.controllers.domicilio;

import com.example.buensabor.entities.domicilio.Localidad;
import com.example.buensabor.services.domicilio.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:59787"})
@RestController
@RequestMapping("/localidades")//ruta principal
public class LocalidadController {

    @Autowired
    LocalidadService localidadService;

    @GetMapping("/todos")
    public List<Localidad> getLocalidades() {
        return localidadService.getLocalidades();
    }

}
