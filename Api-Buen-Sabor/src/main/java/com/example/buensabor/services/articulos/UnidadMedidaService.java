package com.example.buensabor.services.articulos;

import com.example.buensabor.entities.articulos.UnidadMedidaEntity;
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

    public List<UnidadMedidaEntity> getUnidades() {
        return (ArrayList<UnidadMedidaEntity>) unidadMedidaRepository.findAll();
    }

    public Optional<UnidadMedidaEntity> getUnidadById(Long id) {
        return unidadMedidaRepository.findById(id);
    }

    public UnidadMedidaEntity saveOrUpdateUnidad(UnidadMedidaEntity unidad) {
        return (UnidadMedidaEntity) unidadMedidaRepository.save(unidad);
    }

    public UnidadMedidaEntity updateUnidad(UnidadMedidaEntity unidad) {
        return (UnidadMedidaEntity) unidadMedidaRepository.save(unidad);
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
