package com.example.buensabor.services.cliente;

import com.example.buensabor.entities.cliente.Domicilio;
import com.example.buensabor.repositories.cliente.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService {

    @Autowired
    DomicilioRepository domicilioRepository;

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

    public boolean deleteDomicilioById(Long id) {
        try {
            domicilioRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
