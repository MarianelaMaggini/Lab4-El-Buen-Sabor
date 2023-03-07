package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.UnidadMedida;
import com.example.buensabor.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnidadMedidaRepository extends BaseRepository<UnidadMedida,Long> {

    @Query(value = "SELECT u.* \n" +
                   "FROM receta_elaborado r \n" +
                   "INNER JOIN unidad_medida u ON u.id = r.id_unidad_medida \n" +
                   "WHERE r.id_articulo = :idArticulo", nativeQuery = true)
    Optional<UnidadMedida> getUnidadMedidaByIdArticulo(@Param("idArticulo") Long idArticulo);
}
