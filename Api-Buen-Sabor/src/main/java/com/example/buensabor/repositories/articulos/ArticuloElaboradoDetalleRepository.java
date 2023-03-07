package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.ArticuloElaboradoDetalle;
import com.example.buensabor.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticuloElaboradoDetalleRepository extends BaseRepository<ArticuloElaboradoDetalle,Long> {

    @Query(value = "SELECT * FROM articulo_elaborado_detalle a WHERE a.id_articulo = :idArticulo LIMIT 1", nativeQuery = true)
    Optional<ArticuloElaboradoDetalle> getArticuloDetalleByIdArticulo(@Param("idArticulo") Long idArticulo);
}
