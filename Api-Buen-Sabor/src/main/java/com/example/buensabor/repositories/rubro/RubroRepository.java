package com.example.buensabor.repositories.rubro;

import com.example.buensabor.entities.rubro.Rubro;
import com.example.buensabor.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RubroRepository extends BaseRepository<Rubro, Long> {

    List<Rubro> findByFechaBajaIsNull();

    Optional<Rubro> findByIdAndFechaBajaIsNull(Long id);
}
