package com.example.buensabor.repositories.domicilio;

import com.example.buensabor.entities.domicilio.Domicilio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends CrudRepository <Domicilio,Long>{
}
