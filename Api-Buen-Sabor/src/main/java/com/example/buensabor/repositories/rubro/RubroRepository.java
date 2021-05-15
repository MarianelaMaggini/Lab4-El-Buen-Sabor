package com.example.buensabor.repositories.rubro;

import com.example.buensabor.entities.articulos.ArticuloEntity;
import com.example.buensabor.entities.rubro.RubroEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubroRepository extends CrudRepository <RubroEntity,Long>{
}
