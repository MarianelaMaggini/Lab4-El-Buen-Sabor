package com.example.buensabor.services.articulos;

import com.example.buensabor.entities.articulos.UnidadMedida;
import com.example.buensabor.repositories.articulos.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnidadMedidaService {

    @Autowired
    UnidadMedidaRepository unidadMedidaRepository;

    public List<UnidadMedida> getUnidades() {
        return (ArrayList<UnidadMedida>) unidadMedidaRepository.findAll();
    }

    public Optional<UnidadMedida> getUnidadById(Long id) {
        return unidadMedidaRepository.findById(id);
    }

    public UnidadMedida saveOrUpdateUnidad(UnidadMedida unidad) {
        return (UnidadMedida) unidadMedidaRepository.save(unidad);
    }

    public UnidadMedida updateUnidad(UnidadMedida unidad) {
        return (UnidadMedida) unidadMedidaRepository.save(unidad);
    }

    public boolean deleteUnidadById(Long id) {
        try {
            unidadMedidaRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
