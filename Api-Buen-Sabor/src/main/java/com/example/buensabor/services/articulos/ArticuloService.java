package com.example.buensabor.services.articulos;

import com.example.buensabor.entities.articulos.Articulo;
import com.example.buensabor.repositories.articulos.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArticuloService {

    private final ArticuloRepository articuloRepository;

    @Autowired
    public ArticuloService (ArticuloRepository articuloRepository){
        this.articuloRepository = articuloRepository;
    }

    public List<Articulo> getArticulosSinFechaDeBaja() {
        return articuloRepository.findArticuloByFechaBajaIsNull();
    }

    public List<Articulo> getArticulos() {
        return (List<Articulo>) articuloRepository.findAll();
    }

    public Optional<Articulo> getArticuloById(Long id) {
        List<Long> articulosIdInsumos = articuloRepository.getIdArticuloInsumosByElaborado(id);
        Optional<Articulo> articulo = articuloRepository.findById(id);
        if (articulo.get().getTipoArticulo().getId() == 2) {
            Double precioTotal = 0.0;
            for (Long idArticulo : articulosIdInsumos){
                Double precio = articuloRepository.getPrecioInsumosByElaborado(idArticulo);
                Double cantidad = articuloRepository.getCantidadInsumosByElaborado(articulo.get().getId(), idArticulo);
                if(precio != null && cantidad != null) {
                    precioTotal += (precio * cantidad) * 1.5;
                }
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

    public List<Articulo> getArticuloByIdRubro(Long id) {
        List<Articulo> articulos = articuloRepository.findByIdRubro(id);
        return getArticulos(id, articulos);
    }

    public List<Articulo> getArticuloByIdTipoArticulo(Long id) {
        return articuloRepository.findByTipoArticuloIdAndFechaBajaIsNull(id);
    }

    /**
     * Guardo en un listado de articulos los asociados al tipo articulos
     * luego recorro y seteo el atributo TRANSIENT con el precio elaborado
     * caso contrario con el precio NO elaborado
     *
     * @param id Long
     * @return List<Articulo>
     */
    public List<Articulo> getArticuloByIdTipoArticuloWithPrice(Long id) {
        List<Articulo> articulos = articuloRepository.findByTipoArticuloIdAndFechaBajaIsNull(id);
        return getArticulos(id, articulos);
    }

    private List<Articulo> getArticulos(Long id, List<Articulo> articulos) {
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

}
