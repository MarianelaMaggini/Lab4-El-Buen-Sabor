package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.ArticuloEntity;
import com.example.buensabor.entities.articulos.RecetaElaboradoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaElaboradoRepository extends CrudRepository <RecetaElaboradoEntity,Long>{
}
