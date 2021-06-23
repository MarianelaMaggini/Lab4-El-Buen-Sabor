package com.example.buensabor.controllers.domicilio;

import com.example.buensabor.entities.domicilio.Domicilio;
import com.example.buensabor.services.domicilio.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/domicilios")//ruta principal
public class DomicilioController {

    @Autowired
    DomicilioService domicilioService;

    @GetMapping("/todos")
    public List<Domicilio> getDomicilios() {
        return domicilioService.getDomicilios();
    }

    @GetMapping("/{id}")
    public Optional<Domicilio> getDomicilioById(@PathVariable("id") Long id) {
        return domicilioService.getDomicilioById(id);
    }

    @PostMapping()
    public Domicilio saveOrUpdateDomicilio(@RequestBody Domicilio domicilio) {
        return domicilioService.saveOrUpdateDomicilio(domicilio);
    }
}
