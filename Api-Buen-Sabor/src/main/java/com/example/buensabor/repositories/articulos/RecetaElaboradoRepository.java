package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.RecetaElaborado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaElaboradoRepository extends CrudRepository <RecetaElaborado,Long>{
}
