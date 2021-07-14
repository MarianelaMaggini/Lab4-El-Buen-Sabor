package com.example.buensabor.services.articulos;

import com.example.buensabor.entities.articulos.Inventario;
import com.example.buensabor.repositories.articulos.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    @Autowired
    InventarioRepository inventarioRepository;

    /**
     * Cargo en un listado de inventarios los inventarios de la base de datos
     * Recorro y seteo el precioVenta TRANSIENT (no se persite en la base) con el metodo damePrecioVenta
     * de la clase Inventario
     * Retorno esos inventarios con el precio de venta incluido
     * */
    public List<Inventario> getInventarioCompleto() {
        return (List<Inventario>) inventarioRepository.findAll();
    }

    public Optional<Inventario> getInventarioById(Long id) {
        return inventarioRepository.findById(id);
    }

    public Inventario saveOrUpdateInventario(Inventario inventario) {
        return (Inventario) inventarioRepository.save(inventario);
    }

    public Inventario updateInventario(Inventario inventario) {
        return (Inventario) inventarioRepository.save(inventario);
    }

}
