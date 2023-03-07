package com.example.buensabor.services.domicilio;

import com.example.buensabor.entities.domicilio.Localidad;
import com.example.buensabor.repositories.domicilio.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalidadService {

    @Autowired
    LocalidadRepository localidadRepository;

    public List<Localidad> getLocalidades(){
        return (List<Localidad>) localidadRepository.findAll();
    }

    public Optional<Localidad> getLocalidadById(Long id) {
        return localidadRepository.findById(id);
    }

    public Localidad saveOrUpdateLocalidad(Localidad localidad) {
        return (Localidad) localidadRepository.save(localidad);
    }
}
