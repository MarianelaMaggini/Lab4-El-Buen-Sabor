package com.example.buensabor.services.articulos;

import com.example.buensabor.entities.articulos.TipoArticulo;
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

    public List<TipoArticulo> getTiposArticulo() {
        return (ArrayList<TipoArticulo>) tipoArticuloRepository.findAll();
    }

    public Optional<TipoArticulo> getTipoArticuloById(Long id) {
        return tipoArticuloRepository.findById(id);
    }

    public TipoArticulo saveOrUpdateTipoArticulo(TipoArticulo tipoArticulo) {
        return (TipoArticulo) tipoArticuloRepository.save(tipoArticulo);
    }

    public TipoArticulo updateTipoArticulo(TipoArticulo tipoArticulo) {
        return (TipoArticulo) tipoArticuloRepository.save(tipoArticulo);
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
