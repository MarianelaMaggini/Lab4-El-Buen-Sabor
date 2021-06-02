package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.HistoricoArticulo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoArticuloRepository extends CrudRepository <HistoricoArticulo,Long>{

    @Query("SELECT h.precioCompra from HistoricoArticulo h")
    List<Double> getPrecioCompra();
}
