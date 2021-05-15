package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.ArticuloEntity;
import com.example.buensabor.entities.articulos.HistoricoArticuloEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoArticuloRepository extends CrudRepository <HistoricoArticuloEntity,Long>{
}
