package com.example.buensabor.controllers.configuracion;

import com.example.buensabor.entities.configuracion.Configuracion;
import com.example.buensabor.services.configuracion.ConfiguracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/configuraciones")//ruta principal
public class ConfiguracionController {

    private final ConfiguracionService configuracionService;

    @Autowired
    public ConfiguracionController(ConfiguracionService configuracionService) {
        this.configuracionService = configuracionService;
    }
    @GetMapping("/todos")
    public List<Configuracion> getConfiguracion(){
        return this.configuracionService.getConfiguraciones();
    }
}
