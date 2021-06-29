package com.example.buensabor.repositories.comprobantes;

import com.example.buensabor.entities.comprobantes.DetalleFactura;
import com.example.buensabor.entities.comprobantes.DetallePedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleFacturaRepository extends CrudRepository <DetalleFactura,Long>{

    @Query(value = "SELECT * FROM detalle_factura d WHERE d.id_factura = :idFactura", nativeQuery = true)
    List<DetalleFactura> getDetalleByIdFactura(@Param("idFactura") Long idFactura);
}
