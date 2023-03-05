package com.example.buensabor.repositories.comprobantes;

import com.example.buensabor.entities.comprobantes.TipoEnvio;
import com.example.buensabor.repositories.base.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoEnvioRepository extends IBaseRepository<TipoEnvio,Long> {
}
