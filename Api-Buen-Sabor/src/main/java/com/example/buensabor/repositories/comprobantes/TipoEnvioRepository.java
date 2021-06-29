package com.example.buensabor.repositories.comprobantes;

import com.example.buensabor.entities.comprobantes.TipoEnvio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEnvioRepository extends CrudRepository<TipoEnvio,Long>{
}
