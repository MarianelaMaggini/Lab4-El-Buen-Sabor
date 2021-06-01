package com.example.buensabor.repositories.cliente;

import com.example.buensabor.entities.cliente.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends CrudRepository <Rol,Long>{
}
