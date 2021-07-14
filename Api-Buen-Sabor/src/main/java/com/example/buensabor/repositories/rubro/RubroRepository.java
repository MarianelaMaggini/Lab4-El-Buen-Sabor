package com.example.buensabor.repositories.rubro;

import com.example.buensabor.entities.rubro.Rubro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RubroRepository extends CrudRepository <Rubro,Long>{

    List<Rubro> findByFechaBajaIsNull();
}
