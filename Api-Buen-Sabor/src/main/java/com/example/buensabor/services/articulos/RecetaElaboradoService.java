package com.example.buensabor.services.articulos;

import com.example.buensabor.entities.articulos.RecetaElaboradoEntity;
import com.example.buensabor.repositories.articulos.RecetaElaboradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecetaElaboradoService {

    @Autowired
    RecetaElaboradoRepository recetaElaboradoRepository;

    public List<RecetaElaboradoEntity> getRecetas() {
        return (ArrayList<RecetaElaboradoEntity>) recetaElaboradoRepository.findAll();
    }

    public Optional<RecetaElaboradoEntity> getRecetaById(Long id) {
        return recetaElaboradoRepository.findById(id);
    }

    public RecetaElaboradoEntity saveOrUpdateReceta(RecetaElaboradoEntity receta) {
        return (RecetaElaboradoEntity) recetaElaboradoRepository.save(receta);
    }

    public RecetaElaboradoEntity updateReceta(RecetaElaboradoEntity receta) {
        return (RecetaElaboradoEntity) recetaElaboradoRepository.save(receta);
    }

    public boolean deleteRecetaById(Long id) {
        try {
            recetaElaboradoRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
