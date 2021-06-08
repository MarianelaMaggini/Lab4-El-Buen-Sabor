package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.Articulo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends CrudRepository<Articulo, Long> {

    /**
     * SELECCIONO LOS ARTICULOS POR EL ID DEL RUBRO
     * @param idRubro
     * @return
     */
    @Query(value = "SELECT a FROM Articulo a WHERE a.rubro.id = :idRubro")
    List<Articulo> findByIdRubro(@Param("idRubro") Long idRubro);

    /**
     * SELECCIONO LOS ARTICULOS POR EL ID DEL TIPO ARTICULO
     * @param idTipo
     * @return
     */
    List<Articulo> findByTipoArticuloId(@Param("idTipo") Long idTipo);

    /**
     * SELECCIONO LOS ARTICULOS POR EL ID DEL TIPO ARTICULO U OTRO ID DEL TIPO ARTICULO
     * @param idTipoUno
     * @param idTipoDos
     * @return
     */
    List<Articulo> findByTipoArticuloIdOrTipoArticuloId(@Param("idTipoUno") Long idTipoUno, @Param("idTipoDos") Long idTipoDos);

    @Query(value = "select (ha.precio_compra * r.cantidad) * 1.5 as precio from articulo a\n" +
            "inner join articulo_elaborado_detalle aed on aed.id_articulo = a.id \n" +
            "inner join receta_elaborado r on r.id_articulo_elaborado_detalle = aed.id\n" +
            "inner join historico_articulo ha on ha.id_articulo = r.id_articulo\n" +
            "where aed.id_articulo = :idArticulo order by ha.id desc limit 1", nativeQuery = true)
    Double getPrecioElaborado(@Param("idArticulo") Long idArticulo);


    //PRUEBAS
    // retorna los id del articulo que son usados para el id del articulo del articulo elaborado detalle
    @Query(value = "select r.id_articulo from articulo a\n" +
            "inner join articulo_elaborado_detalle aed on aed.id_articulo = a.id \n" +
            "inner join receta_elaborado r on r.id_articulo_elaborado_detalle = aed.id \n" +
            "where aed.id_articulo = :idArticulo", nativeQuery = true)
    List<Long> getIdArticuloInsumosByElaborado(@Param("idArticulo") Long idArticulo);

    // retorna precio asociado al id articulo de la receta por orden de id historico descendente y un limit 1 para no repetirse
    @Query(value = "select ha.precio_compra  as precio from articulo a\n" +
            "inner join historico_articulo ha on ha.id_articulo = a.id \n" +
            "inner join receta_elaborado r on r.id_articulo = ha.id_articulo\n" +
            "where ha.id_articulo = :idArticulo order by ha.id desc limit 1", nativeQuery = true)
    double getPrecioInsumosByElaborado(@Param("idArticulo") Long idArticulo);

    // selecciona la cantidad relacionada al articulo id
    @Query(value = "select r.cantidad from articulo a\n" +
            "inner join articulo_elaborado_detalle aed on aed.id_articulo = a.id \n" +
            "inner join receta_elaborado r on r.id_articulo_elaborado_detalle = aed.id \n" +
            "where aed.id_articulo = :idArticuloDetalle and r.id_articulo = :idArticulo", nativeQuery = true)
    double getCantidadInsumosByElaborado(@Param("idArticuloDetalle") Long idArticuloDetalle, @Param("idArticulo") Long idArticulo);
}
