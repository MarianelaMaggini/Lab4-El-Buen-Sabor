package com.example.buensabor.repositories.comprobantes;

import com.example.buensabor.entities.comprobantes.DetallePedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends CrudRepository <DetallePedido,Long>{
}
