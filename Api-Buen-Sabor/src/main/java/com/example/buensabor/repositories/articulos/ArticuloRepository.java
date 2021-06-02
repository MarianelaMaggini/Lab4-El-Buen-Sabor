package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.Articulo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends CrudRepository<Articulo, Long> {

    @Query(value = "SELECT a FROM Articulo a WHERE a.rubro.id = :idRubro")
    List<Articulo> findByIdRubro(@Param("idRubro") Long idRubro);

    //@Query(value="SELECT a FROM Articulo a WHERE a.tipoArticuloEntity.id = :idTipoUno OR a.tipoArticuloEntity.id = :idTipoDos")
    List<Articulo> findByTipoArticuloIdOrTipoArticuloId(@Param("idTipoUno") Long idTipoUno, @Param("idTipoDos") Long idTipoDos);
}
