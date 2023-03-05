package com.example.buensabor.repositories.rubro;

import com.example.buensabor.entities.rubro.Rubro;
import com.example.buensabor.repositories.base.IBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRubroRepository extends IBaseRepository<Rubro, Long> {

    List<Rubro> findByFechaBajaIsNull();

    Optional<Rubro> findByIdAndFechaBajaIsNull(Long id);
}
