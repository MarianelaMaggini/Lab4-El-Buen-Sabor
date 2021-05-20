package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.ArticuloEntity;
import com.example.buensabor.entities.articulos.HistoricoArticuloEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface HistoricoArticuloRepository extends CrudRepository <HistoricoArticuloEntity,Long>{

    @Query("SELECT h.precioCompra from HistoricoArticuloEntity h")
    List<Double> getPrecioCompra();
}
