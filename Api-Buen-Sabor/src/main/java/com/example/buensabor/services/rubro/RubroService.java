package com.example.buensabor.services.rubro;

import com.example.buensabor.entities.rubro.RubroEntity;
import com.example.buensabor.repositories.rubro.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RubroService {

    @Autowired
    RubroRepository rubroRepository;

    public List<RubroEntity> getRubros() {
        return (ArrayList<RubroEntity>) rubroRepository.findAll();
    }

    public Optional<RubroEntity> getRubroById(Long id) {
        return rubroRepository.findById(id);
    }

    public RubroEntity saveOrUpdateRubro(RubroEntity rubro) {
        return (RubroEntity) rubroRepository.save(rubro);
    }

    public RubroEntity updateRubro(RubroEntity rubro) {
        return (RubroEntity) rubroRepository.save(rubro);
    }

    public boolean deleteRubroById(Long id) {
        try {
            rubroRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
