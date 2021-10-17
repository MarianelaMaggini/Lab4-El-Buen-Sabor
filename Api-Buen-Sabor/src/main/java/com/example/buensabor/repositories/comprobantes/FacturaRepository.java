package com.example.buensabor.repositories.comprobantes;

import com.example.buensabor.entities.comprobantes.Factura;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturaRepository extends CrudRepository <Factura,Long>{
    Optional<Factura> findByPedidoNumeroPedido(Long numeroPedido);
}
