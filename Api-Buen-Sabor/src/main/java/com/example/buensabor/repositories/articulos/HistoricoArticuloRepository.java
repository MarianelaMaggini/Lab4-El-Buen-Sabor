package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.HistoricoArticulo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoArticuloRepository extends CrudRepository <HistoricoArticulo,Long>{

}
