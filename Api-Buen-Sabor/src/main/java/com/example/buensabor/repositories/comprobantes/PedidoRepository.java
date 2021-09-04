package com.example.buensabor.repositories.comprobantes;

import com.example.buensabor.entities.comprobantes.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends CrudRepository <Pedido,Long> {

    @Query(value = "SELECT p FROM Pedido p WHERE p.usuario.id = :idUsuario")
    List<Pedido> findByIdUsuario(@Param("idUsuario") Long idUsuario);
}
