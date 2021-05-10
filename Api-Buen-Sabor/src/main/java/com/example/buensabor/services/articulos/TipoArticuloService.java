package com.example.buensabor.services.articulos;

import com.example.buensabor.entities.articulos.TipoArticuloEntity;
import com.example.buensabor.repositories.articulos.TipoArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TipoArticuloService {

    @Autowired
    TipoArticuloRepository tipoArticuloRepository;

    public List<TipoArticuloEntity> getTiposArticulo() {
        return (ArrayList<TipoArticuloEntity>) tipoArticuloRepository.findAll();
    }

    public Optional<TipoArticuloEntity> getTipoArticuloById(Long id) {
        return tipoArticuloRepository.findById(id);
    }

    public TipoArticuloEntity saveOrUpdateTipoArticulo(TipoArticuloEntity tipoArticulo) {
        return (TipoArticuloEntity) tipoArticuloRepository.save(tipoArticulo);
    }

    public TipoArticuloEntity updateTipoArticulo(TipoArticuloEntity tipoArticulo) {
        return (TipoArticuloEntity) tipoArticuloRepository.save(tipoArticulo);
    }

    public boolean deleteTipoArticuloById(Long id) {
        try {
            tipoArticuloRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
