package com.example.buensabor.controllers.comprobantes;

import com.example.buensabor.entities.comprobantes.TipoEnvio;
import com.example.buensabor.services.comprobantes.TipoEnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tiposEnvio") //ruta principal
public class TipoEnvioController {

    @Autowired
    TipoEnvioService tipoEnvioService;

    @GetMapping("/todos")
    public List<TipoEnvio> getTiposEnvio() {
        return tipoEnvioService.getTiposEnvio();
    }

    @GetMapping("/{id}")
    public Optional<TipoEnvio> getTipoEnvioById(@PathVariable("id") Long id) {
        return tipoEnvioService.getTipoEnvioById(id);
    }
}
