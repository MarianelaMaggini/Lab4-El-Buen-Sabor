package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.RecetaElaborado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecetaElaboradoRepository extends CrudRepository <RecetaElaborado,Long>{

    @Query(value = "SELECT * FROM receta_elaborado r WHERE r.id_articulo_elaborado_detalle = :idArticulo AND r.fecha_baja IS NULL", nativeQuery = true)
    List<RecetaElaborado> getRecetaByIdArticulo(@Param("idArticulo") Long idArticulo);

    @Query(value = "SELECT * FROM receta_elaborado r WHERE r.id_articulo_elaborado_detalle = :idArticulo", nativeQuery = true)
    List<RecetaElaborado> getRecetaHistoricaByIdArticulo(@Param("idArticulo") Long idArticulo);

}
