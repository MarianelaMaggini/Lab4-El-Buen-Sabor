package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.ArticuloEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository extends CrudRepository<ArticuloEntity,Long> {

    @Query(value="SELECT a FROM ArticuloEntity a WHERE a.rubroEntity.id = :idRubro")
    List<ArticuloEntity> findByIdRubro(@Param("idRubro") Long idRubro);

}
