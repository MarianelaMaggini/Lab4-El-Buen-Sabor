package com.example.buensabor.repositories.comprobantes;

import com.example.buensabor.entities.comprobantes.Factura;
import com.example.buensabor.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturaRepository extends BaseRepository<Factura,Long> {
    Optional<Factura> findByPedidoId(Long numeroPedido);
}
