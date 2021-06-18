package com.example.buensabor.services.configuracion;

import com.example.buensabor.entities.configuracion.Configuracion;
import com.example.buensabor.repositories.configuracion.ConfiguracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfiguracionService {

    private final ConfiguracionRepository configuracionRepository;

    @Autowired
    public ConfiguracionService(ConfiguracionRepository configuracionRepository) {
        this.configuracionRepository = configuracionRepository;
    }

    public List<Configuracion> getConfiguraciones(){
        return (List<Configuracion>) this.configuracionRepository.findAll();
    }

    public String getToken(){
        return this.configuracionRepository.getTokenMercadoPago();
    }
}
