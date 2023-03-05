package com.example.buensabor.services.articulos;

import com.example.buensabor.entities.articulos.ArticuloElaboradoDetalle;
import com.example.buensabor.repositories.articulos.IArticuloElaboradoDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloElaboradoDetalleService {

    @Autowired
    IArticuloElaboradoDetalleRepository articuloElaboradoDetalleRepository;

    public List<ArticuloElaboradoDetalle> getArticuloDetalles() {
        return (List<ArticuloElaboradoDetalle>) articuloElaboradoDetalleRepository.findAll();
    }

    public Optional<ArticuloElaboradoDetalle> getArticuloDetalleById(Long id) {
        return articuloElaboradoDetalleRepository.findById(id);
    }

    public Optional<ArticuloElaboradoDetalle> getArticuloDetalleByIdArticulo(Long id) {
        return articuloElaboradoDetalleRepository.getArticuloDetalleByIdArticulo(id);
    }

    public ArticuloElaboradoDetalle saveOrUpdateArticuloDetalle(ArticuloElaboradoDetalle articuloDetalle) {
        return articuloElaboradoDetalleRepository.save(articuloDetalle);
    }

    public ArticuloElaboradoDetalle updateArticuloDetalle(ArticuloElaboradoDetalle articuloDetalle) {
        return articuloElaboradoDetalleRepository.save(articuloDetalle);
    }

}
