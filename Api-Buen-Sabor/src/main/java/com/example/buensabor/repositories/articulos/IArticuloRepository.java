package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.Articulo;
import com.example.buensabor.repositories.base.IBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IArticuloRepository extends IBaseRepository<Articulo, Long> {

    /**
     * TRAE TODOS LOS ARTICULOS SIN FECHA DE BAJA
     * @return Articulos
     */
    List<Articulo> findArticuloByFechaBajaIsNull();

    /**
     * SELECCIONO LOS ARTICULOS POR EL ID DEL RUBRO
     * @param idRubro
     * @return
     */
    @Query(value = "SELECT a FROM Articulo a WHERE a.rubro.id = :idRubro and a.fechaBaja is null")
    List<Articulo> findByIdRubro(@Param("idRubro") Long idRubro);

    /**
     * SELECCIONO LOS ARTICULOS POR EL ID DEL TIPO ARTICULO ELABORADO Y NO ELABORADO
     * @param idTipoArticulo
     * @return
     */
    @Query(value = "SELECT DISTINCT a.rubro.id FROM Articulo a WHERE a.tipoArticulo.id <> :idTipoArticulo")
    List<Long> getRubroIdUniqueByTipoArticuloNotInsumo(@Param("idTipoArticulo") Long idTipoArticulo);
    /**
     * SELECCIONO LOS ARTICULOS POR EL ID DEL TIPO ARTICULO
     * @param idTipo
     * @return
     */
    List<Articulo> findByTipoArticuloIdAndFechaBajaIsNull(@Param("idTipo") Long idTipo);

    /**
     * retorna los id del articulo que son usados para el id del articulo del articulo elaborado detalle
     * @param idArticulo
     * @return id del articulo
    */
    @Query(value = "select r.id_articulo from articulo a\n" +
            "inner join articulo_elaborado_detalle aed on aed.id_articulo = a.id \n" +
            "inner join receta_elaborado r on r.id_articulo_elaborado_detalle = aed.id \n" +
            "where aed.id_articulo = :idArticulo and r.fecha_baja is null", nativeQuery = true)
    List<Long> getIdArticuloInsumosByElaborado(@Param("idArticulo") Long idArticulo);

    /**
     * retorna precio asociado al id articulo de la receta por orden de id
     * historico descendente y un limit 1 para no repetirse
     * @param idArticulo
     * @return precio
     */
    @Query(value = "select ha.precio_compra  as precio from articulo a\n" +
            "inner join historico_articulo ha on ha.id_articulo = a.id \n" +
            "inner join receta_elaborado r on r.id_articulo = ha.id_articulo\n" +
            "where ha.id_articulo = :idArticulo order by ha.id desc limit 1", nativeQuery = true)
    Double getPrecioInsumosByElaborado(@Param("idArticulo") Long idArticulo);

    /**
     * selecciona la cantidad relacionada al articulo id
     * @param idArticuloDetalle
     * @param idArticulo
     * @return cantidad
     */
    @Query(value = "select r.cantidad from articulo a\n" +
            "inner join articulo_elaborado_detalle aed on aed.id_articulo = a.id \n" +
            "inner join receta_elaborado r on r.id_articulo_elaborado_detalle = aed.id \n" +
            "where aed.id_articulo = :idArticuloDetalle and r.id_articulo = :idArticulo and r.fecha_baja is null", nativeQuery = true)
    Double getCantidadInsumosByElaborado(@Param("idArticuloDetalle") Long idArticuloDetalle, @Param("idArticulo") Long idArticulo);

}
