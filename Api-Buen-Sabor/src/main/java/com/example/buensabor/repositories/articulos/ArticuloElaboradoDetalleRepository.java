package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.ArticuloElaboradoDetalleEntity;
import com.example.buensabor.entities.articulos.ArticuloEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloElaboradoDetalleRepository extends CrudRepository <ArticuloElaboradoDetalleEntity,Long>{
}
