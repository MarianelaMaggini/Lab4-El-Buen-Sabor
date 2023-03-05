package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.RecetaElaborado;
import com.example.buensabor.repositories.base.IBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecetaElaboradoRepository extends IBaseRepository<RecetaElaborado,Long> {

    @Query(value = "SELECT * FROM receta_elaborado r WHERE r.id_articulo_elaborado_detalle = :idArticuloDetalle and r.fecha_baja is null", nativeQuery = true)
    List<RecetaElaborado> getRecetaByIdAed(@Param("idArticuloDetalle") Long idArticuloDetalle);

    @Query(value = "SELECT * FROM receta_elaborado r WHERE r.id_articulo_elaborado_detalle = :idArticulo", nativeQuery = true)
    List<RecetaElaborado> getRecetaHistoricaByIdAed(@Param("idArticulo") Long idArticulo);

}
