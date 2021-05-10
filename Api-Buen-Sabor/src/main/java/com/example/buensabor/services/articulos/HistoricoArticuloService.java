package com.example.buensabor.services.articulos;

import com.example.buensabor.entities.articulos.HistoricoArticuloEntity;
import com.example.buensabor.repositories.articulos.HistoricoArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HistoricoArticuloService {

    @Autowired
    HistoricoArticuloRepository historicoArticuloRepository;

    public List<HistoricoArticuloEntity> getHistoricoArticulos() {
        return (ArrayList<HistoricoArticuloEntity>) historicoArticuloRepository.findAll();
    }

    public Optional<HistoricoArticuloEntity> getHistoricoArticuloById(Long id) {
        return historicoArticuloRepository.findById(id);
    }

    public HistoricoArticuloEntity saveOrUpdateHistoricoArticulo(HistoricoArticuloEntity historicoArticulo) {
        return (HistoricoArticuloEntity) historicoArticuloRepository.save(historicoArticulo);
    }

    public HistoricoArticuloEntity updateHistoricoArticulo(HistoricoArticuloEntity historicoArticulo) {
        return (HistoricoArticuloEntity) historicoArticuloRepository.save(historicoArticulo);
    }

    public boolean deleteHistoricoArticuloById(Long id) {
        try {
            historicoArticuloRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
