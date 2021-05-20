package com.example.buensabor.services.configuracion;

import com.example.buensabor.entities.configuracion.ConfiguracionEntity;
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

    public List<ConfiguracionEntity> getConfiguraciones(){
        return (List<ConfiguracionEntity>) this.configuracionRepository.findAll();
    }
}
