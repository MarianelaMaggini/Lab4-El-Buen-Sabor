package com.example.buensabor.repositories.comprobantes;

import com.example.buensabor.entities.comprobantes.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository <Pedido,Long> {
}
