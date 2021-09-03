package com.example.buensabor.services.domicilio;

import com.example.buensabor.entities.domicilio.Localidad;
import com.example.buensabor.repositories.domicilio.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadService {

    @Autowired
    LocalidadRepository localidadRepository;

    public List<Localidad> getLocalidades(){
        return (List<Localidad>) localidadRepository.findAll();
    }
}
