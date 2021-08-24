package com.example.buensabor.services.domicilio;

import com.example.buensabor.entities.domicilio.Domicilio;
import com.example.buensabor.repositories.domicilio.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService {

    @Autowired
    DomicilioRepository domicilioRepository;

    public List<Domicilio> getDomiciliosSinFechaDeBaja() {
        return (ArrayList<Domicilio>) domicilioRepository.findByFechaBajaIsNull();
    }

    public List<Domicilio> getDomicilios() {
        return (ArrayList<Domicilio>) domicilioRepository.findAll();
    }

    public Optional<Domicilio> getDomicilioById(Long id) {
        return domicilioRepository.findById(id);
    }

    public Domicilio saveOrUpdateDomicilio(Domicilio domicilio) {
        return (Domicilio) domicilioRepository.save(domicilio);
    }

    public Domicilio updateDomicilio(Domicilio domicilio) {
        return (Domicilio) domicilioRepository.save(domicilio);
    }

    public List<Domicilio> getDomiciliosByUsuarioId(Long idUsuario) {
        return domicilioRepository.findByIdUsuario(idUsuario);
    }
}
