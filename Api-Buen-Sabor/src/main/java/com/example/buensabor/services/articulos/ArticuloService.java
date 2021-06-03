package com.example.buensabor.services.articulos;

import com.example.buensabor.entities.articulos.Articulo;
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

    /**
     * Cargo en un listado de inventarios los inventarios de la base de datos
     * Recorro y seteo el precioVenta TRANSIENT (no se persite en la base) con el metodo damePrecioVenta
     * de la clase Inventario
     * Retorno esos inventarios con el precio de venta incluido
     * */
    public List<Articulo> getArticulos() {
        List<Articulo> articulos = (ArrayList<Articulo>) articuloRepository.findAll();
        for (Articulo a : articulos){
            a.setPrecioVenta(a.damePrecioVenta());
        }
        return articulos;
    }

    public Optional<Articulo> getArticuloById(Long id) {
        return articuloRepository.findById(id);
    }

    public Articulo saveOrUpdateArticulo(Articulo articulo) {
        return (Articulo) articuloRepository.save(articulo);
    }

    public Articulo updateArticulo(Articulo articulo) {
        return (Articulo) articuloRepository.save(articulo);
    }

    public List<Articulo> getArticuloByIdRubro(Long id) {
        return articuloRepository.findByIdRubro(id);
    }

    public List<Articulo> getArticuloByIdTipoArticulo(Long idUno, Long idDos){
        return articuloRepository.findByTipoArticuloIdOrTipoArticuloId(idUno, idDos);
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
