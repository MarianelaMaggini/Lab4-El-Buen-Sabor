package com.example.buensabor.repositories.domicilio;

import com.example.buensabor.entities.domicilio.Domicilio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomicilioRepository extends CrudRepository <Domicilio,Long>{
    List<Domicilio> findByFechaBajaIsNull();
}
