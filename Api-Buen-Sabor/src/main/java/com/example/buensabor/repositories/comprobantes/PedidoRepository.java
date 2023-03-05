package com.example.buensabor.repositories.comprobantes;

import com.example.buensabor.entities.comprobantes.Pedido;
import com.example.buensabor.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido,Long> {

    @Query(value = "SELECT p FROM Pedido p WHERE p.usuario.id = :idUsuario")
    List<Pedido> findByIdUsuario(@Param("idUsuario") Long idUsuario);

    @Query(value = "SELECT * FROM pedido p WHERE p.id_estado = :pedidoEstadoId", nativeQuery = true)
    List<Pedido> findPedidoByPedidoEstadoId(@Param("pedidoEstadoId") int pedidoEstadoId);

}
