package com.example.buensabor.repositories.comprobantes;

import com.example.buensabor.entities.comprobantes.PedidoEstado;
import com.example.buensabor.repositories.base.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPedidoEstadoRepository extends IBaseRepository<PedidoEstado, Long> {
}
