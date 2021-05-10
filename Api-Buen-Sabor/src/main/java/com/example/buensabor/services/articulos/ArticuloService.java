package com.example.buensabor.services.articulos;

import com.example.buensabor.entities.articulos.ArticuloEntity;
import com.example.buensabor.repositories.articulos.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticuloService {

    @Autowired
    ArticuloRepository articuloRepository;

    public List<ArticuloEntity> getArticulos() {
        return (ArrayList<ArticuloEntity>) articuloRepository.findAll();
    }

    public Optional<ArticuloEntity> getArticuloById(Long id) {
        return articuloRepository.findById(id);
    }

    public ArticuloEntity saveOrUpdateArticulo(ArticuloEntity articulo) {
        return (ArticuloEntity) articuloRepository.save(articulo);
    }

    public ArticuloEntity updateArticulo(ArticuloEntity articulo) {
        return (ArticuloEntity) articuloRepository.save(articulo);
    }

    public boolean deleteArticuloById(Long id) {
        try {
            articuloRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
