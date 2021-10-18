package com.example.buensabor.repositories.comprobantes;

import com.example.buensabor.entities.comprobantes.PedidoEstado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoEstadoRepository extends CrudRepository<PedidoEstado, Long>{
}
