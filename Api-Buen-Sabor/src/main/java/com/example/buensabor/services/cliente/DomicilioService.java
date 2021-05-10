package com.example.buensabor.services.cliente;

import com.example.buensabor.entities.cliente.DomicilioEntity;
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

    public List<DomicilioEntity> getDomicilios() {
        return (ArrayList<DomicilioEntity>) domicilioRepository.findAll();
    }

    public Optional<DomicilioEntity> getDomicilioById(Long id) {
        return domicilioRepository.findById(id);
    }

    public DomicilioEntity saveOrUpdateDomicilio(DomicilioEntity domicilio) {
        return (DomicilioEntity) domicilioRepository.save(domicilio);
    }

    public DomicilioEntity updateDomicilio(DomicilioEntity domicilio) {
        return (DomicilioEntity) domicilioRepository.save(domicilio);
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
