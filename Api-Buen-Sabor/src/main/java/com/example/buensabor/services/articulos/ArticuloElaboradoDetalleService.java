package com.example.buensabor.services.articulos;

import com.example.buensabor.entities.articulos.ArticuloElaboradoDetalleEntity;
import com.example.buensabor.repositories.articulos.ArticuloElaboradoDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloElaboradoDetalleService {

    @Autowired
    ArticuloElaboradoDetalleRepository articuloElaboradoDetalleRepository;

    public List<ArticuloElaboradoDetalleEntity> getArticuloDetalles() {
        return (List<ArticuloElaboradoDetalleEntity>) articuloElaboradoDetalleRepository.findAll();
    }

    public Optional<ArticuloElaboradoDetalleEntity> getArticuloDetalleById(Long id) {
        return articuloElaboradoDetalleRepository.findById(id);
    }

    public ArticuloElaboradoDetalleEntity saveOrUpdateArticuloDetalle(ArticuloElaboradoDetalleEntity articuloDetalle) {
        return (ArticuloElaboradoDetalleEntity) articuloElaboradoDetalleRepository.save(articuloDetalle);
    }

    public ArticuloElaboradoDetalleEntity updateArticuloDetalle(ArticuloElaboradoDetalleEntity articuloDetalle) {
        return (ArticuloElaboradoDetalleEntity) articuloElaboradoDetalleRepository.save(articuloDetalle);
    }

    public boolean deleteArticuloDetalleById(Long id) {
        try {
            articuloElaboradoDetalleRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
