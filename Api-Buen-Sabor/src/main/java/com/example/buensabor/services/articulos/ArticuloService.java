package com.example.buensabor.services.articulos;

import com.example.buensabor.entities.articulos.Articulo;
import com.example.buensabor.entities.articulos.RecetaElaborado;
import com.example.buensabor.repositories.articulos.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloService {

    @Autowired
    ArticuloRepository articuloRepository;

    public List<Articulo> getArticulos() {
        return (List<Articulo>) articuloRepository.findAll();
    }

    public Optional<Articulo> getArticuloById(Long id) {
        List<Long> articulosIdInsumos = articuloRepository.getIdArticuloInsumosByElaborado(id);
        Optional<Articulo> articulo = articuloRepository.findById(id);
        if (articulo.get().getTipoArticulo().getId() == 2) {
            double precioTotal = 0.0;
            for (Long idArticulo : articulosIdInsumos){
                double precio = articuloRepository.getPrecioInsumosByElaborado(idArticulo);
                //double cantidad = articuloRepository.getCantidadInsumosByElaborado(idArticulo);
                //precioTotal += (precio * cantidad) * 1.5;
            }
            articulo.get().setPrecioVenta(precioTotal);
        } else {
            articulo.get().setPrecioVenta(articulo.get().getPrecioNoElaborado());
        }

        return articulo;
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

    /**
     * Metodo para filtrar por tipo articulo no elaborado OR elaborado
     */
    public List<Articulo> getArticuloByIdTipoArticuloOrIdTipoArticulo(Long idUno, Long idDos) {
        return articuloRepository.findByTipoArticuloIdOrTipoArticuloId(idUno, idDos);
    }

    /**
     * Guardo en un listado de articulos los asociados al tipo articulos
     * luego recorro y seteo el atributo TRANSIENT con el precio elaborado
     * caso contrario con el precio NO elaborado
     *
     * @param id Long
     * @return List<Articulo>
     */
    public List<Articulo> getArticuloByIdTipoArticulo(Long id) {
        List<Articulo> articulos = articuloRepository.findByTipoArticuloId(id);
        List<Long> articulosIdInsumos;
        if (id == 2) {
            for (Articulo articulo : articulos) {
                Double precioTotal = 0.0;
                articulosIdInsumos = articuloRepository.getIdArticuloInsumosByElaborado(articulo.getId());
                for (Long idArticulo : articulosIdInsumos){
                    Double precio = articuloRepository.getPrecioInsumosByElaborado(idArticulo);
                    Double cantidad = articuloRepository.getCantidadInsumosByElaborado(articulo.getId(), idArticulo);
                    if(precio != null && cantidad != null) {
                        precioTotal += (precio * cantidad) * 1.5;
                    }
                }
                articulo.setPrecioVenta(precioTotal);
            }
        } else {
            for (Articulo articulo : articulos) {
                articulo.setPrecioVenta(articulo.getPrecioNoElaborado());
            }
        }
        return articulos;
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
