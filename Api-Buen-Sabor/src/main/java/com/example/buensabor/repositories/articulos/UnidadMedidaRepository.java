package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.ArticuloEntity;
import com.example.buensabor.entities.articulos.UnidadMedidaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadMedidaRepository extends CrudRepository <UnidadMedidaEntity,Long>{
}
