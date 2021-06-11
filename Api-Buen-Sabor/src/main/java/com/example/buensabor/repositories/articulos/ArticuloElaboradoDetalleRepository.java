package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.ArticuloElaboradoDetalle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticuloElaboradoDetalleRepository extends CrudRepository <ArticuloElaboradoDetalle,Long>{

    @Query(value = "SELECT * FROM articulo_elaborado_detalle a WHERE a.id_articulo = :idArticulo", nativeQuery = true)
    Optional<ArticuloElaboradoDetalle> getArticuloDetalleByIdArticulo(@Param("idArticulo") Long idArticulo);
}
