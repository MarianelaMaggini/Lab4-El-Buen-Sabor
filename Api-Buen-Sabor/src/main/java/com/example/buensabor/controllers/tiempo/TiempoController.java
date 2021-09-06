package com.example.buensabor.controllers.tiempo;

import com.example.buensabor.services.tiempo.TiempoService;
import com.example.buensabor.utils.Tiempo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
@RestController
@RequestMapping("/tiempos") // Ruta principal
public class TiempoController {

    @Autowired
    TiempoService tiempoService;

    @GetMapping("")
    public Tiempo getTiempo() {
        return tiempoService.getTiempo() ;
    }
}
