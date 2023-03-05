package com.example.buensabor.repositories.comprobantes;

import com.example.buensabor.entities.comprobantes.DetallePedido;
import com.example.buensabor.repositories.base.IBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDetallePedidoRepository extends IBaseRepository<DetallePedido,Long> {

    @Query(value = "SELECT * FROM detalle_pedido d WHERE d.id_pedido = :idPedido", nativeQuery = true)
    List<DetallePedido> getDetalleByIdPedido(@Param("idPedido") Long idPedido);
}
