package com.example.buensabor.repositories.cliente;

import com.example.buensabor.entities.cliente.Domicilio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends CrudRepository <Domicilio,Long>{
}
