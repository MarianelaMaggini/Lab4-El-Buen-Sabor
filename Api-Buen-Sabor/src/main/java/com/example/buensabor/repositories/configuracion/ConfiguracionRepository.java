package com.example.buensabor.repositories.configuracion;

import com.example.buensabor.entities.configuracion.ConfiguracionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracionRepository extends CrudRepository<ConfiguracionEntity,Long> {
}
