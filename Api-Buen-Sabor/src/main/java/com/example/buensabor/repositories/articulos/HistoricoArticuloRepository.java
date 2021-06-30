package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.HistoricoArticulo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoArticuloRepository extends CrudRepository <HistoricoArticulo,Long>{

    @Query(value = "SELECT * FROM historico_articulo h WHERE h.id_articulo = :idArticulo", nativeQuery = true)
    List<HistoricoArticulo> getHistoricoArticuloByIdArticulo(@Param("idArticulo") Long idArticulo);
}
