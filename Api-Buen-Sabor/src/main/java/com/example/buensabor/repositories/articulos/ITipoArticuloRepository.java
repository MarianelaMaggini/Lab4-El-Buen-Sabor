package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.TipoArticulo;
import com.example.buensabor.repositories.base.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoArticuloRepository extends IBaseRepository<TipoArticulo,Long> {
}
