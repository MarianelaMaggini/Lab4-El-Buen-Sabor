package com.example.buensabor.repositories.domicilio;

import com.example.buensabor.entities.domicilio.Localidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocalidadRepository extends CrudRepository<Localidad,Long> {
}
