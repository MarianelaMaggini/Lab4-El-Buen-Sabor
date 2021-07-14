package com.example.buensabor.services.rubro;

import com.example.buensabor.entities.rubro.Rubro;
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

    public List<Rubro> getRubrosSinFechaDeBaja() {
        return (ArrayList<Rubro>) rubroRepository.findByFechaBajaIsNull();
    }

    public List<Rubro> getRubros() {
        return (ArrayList<Rubro>) rubroRepository.findAll();
    }

    public Optional<Rubro> getRubroById(Long id) {
        return rubroRepository.findById(id);
    }

    public Rubro saveOrUpdateRubro(Rubro rubro) {
        return (Rubro) rubroRepository.save(rubro);
    }

    public Rubro updateRubro(Rubro rubro) {
        return (Rubro) rubroRepository.save(rubro);
    }

}
