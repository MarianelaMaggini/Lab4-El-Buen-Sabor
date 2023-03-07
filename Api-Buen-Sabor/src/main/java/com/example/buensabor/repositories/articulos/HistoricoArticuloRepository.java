package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.HistoricoArticulo;
import com.example.buensabor.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoArticuloRepository extends BaseRepository<HistoricoArticulo,Long> {

    @Query(value = "SELECT * FROM historico_articulo h WHERE h.id_articulo = :idArticulo", nativeQuery = true)
    List<HistoricoArticulo> getHistoricoArticuloByIdArticulo(@Param("idArticulo") Long idArticulo);
}
