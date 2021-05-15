package com.example.buensabor.repositories.comprobantes;

import com.example.buensabor.entities.articulos.ArticuloEntity;
import com.example.buensabor.entities.comprobantes.DetalleFacturaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleFacturaRepository extends CrudRepository <DetalleFacturaEntity,Long>{
}
