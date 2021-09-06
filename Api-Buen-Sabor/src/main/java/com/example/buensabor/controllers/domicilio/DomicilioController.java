package com.example.buensabor.controllers.domicilio;

import com.example.buensabor.entities.domicilio.Domicilio;
import com.example.buensabor.services.domicilio.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
@RestController
@RequestMapping("/domicilios")//ruta principal
public class DomicilioController {

    @Autowired
    DomicilioService domicilioService;

    @GetMapping("/todosActivo")
    public List<Domicilio> getDomiciliosSinFechaDeBaja() {
        return domicilioService.getDomiciliosSinFechaDeBaja();
    }

    @GetMapping("/todos")
    public List<Domicilio> getDomicilios() {
        return domicilioService.getDomicilios();
    }

    @GetMapping("/{id}")
    public Optional<Domicilio> getDomicilioById(@PathVariable("id") Long id) {
        return domicilioService.getDomicilioById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public Domicilio saveOrUpdateDomicilio(@RequestBody Domicilio domicilio) {
        return domicilioService.saveOrUpdateDomicilio(domicilio);
    }

    @GetMapping("/usuario")
    public List<Domicilio> getDomiciliosByUserId(@RequestParam("id") Long id) {
        return domicilioService.getDomiciliosByUsuarioId(id);
    }

    @GetMapping("/localidad")
    public List<Domicilio> getDomiciliosByLocalityId(@RequestParam("id") Long id) {
        return domicilioService.getDomiciliosByLocalidadId(id);
    }
}
