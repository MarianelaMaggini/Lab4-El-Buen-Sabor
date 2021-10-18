package com.example.buensabor.repositories.reportes;

import com.example.buensabor.entities.comprobantes.Pedido;
import com.example.buensabor.entities.reportes.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportesRepository extends CrudRepository<Pedido, Long> {

    @Query(value =  "SELECT CONCAT(R2.nombre, ' ', R2.apellido) AS nombreCompleto, COUNT(*) AS numeroPedidos \n" +
                    "FROM pedido R0 \n" +
                    "INNER JOIN factura R1 ON R1.numero_pedido = R0.numero_pedido \n" +
                    "INNER JOIN usuario R2 ON R2.id = R0.id_usuario \n" +
                    "WHERE (R1.fecha >= :fechaDesde AND R1.fecha <= :fechaHasta) \n" +
                    "GROUP BY R0.id_usuario \n" +
                    "ORDER BY numeroPedidos DESC", nativeQuery = true)
    List<CantidadPedidos> getCantidadPedidos(@Param("fechaDesde") String fechaDesde, @Param("fechaHasta") String fechaHasta);

    @Query(value =  "SELECT R1.denominacion AS denominacion, SUM(R0.cantidad) AS cantidad \n" +
                    "FROM detalle_pedido R0 \n" +
                    "INNER JOIN articulo R1 ON R1.id = R0.id_articulo \n" +
                    "INNER JOIN factura R2 ON R2.numero_pedido = R0.id_pedido \n" +
                    "WHERE R1.id_tipo_articulo = 2 AND (R2.fecha >= :fechaDesde AND R2.fecha <= :fechaHasta) \n" +
                    "GROUP BY R1.id \n" +
                    "ORDER BY cantidad DESC", nativeQuery = true)
    List<RankingComidas> getRankingComidas(@Param("fechaDesde") String fechaDesde, @Param("fechaHasta") String fechaHasta);

    @Query(value =  "SELECT SUM(R0.subtotal) - (SUM(R0.subtotal)/1.5) AS monto \n" +
                    "FROM detalle_pedido R0 \n" +
                    "INNER JOIN factura R1 ON R1.numero_pedido = R0.id_pedido \n" +
                    "WHERE (R1.fecha >= :fechaDesde AND R1.fecha <= :fechaHasta)", nativeQuery = true)
    MontoGanancia getMontoGanancia(@Param("fechaDesde") String fechaDesde, @Param("fechaHasta") String fechaHasta);

    @Query(value =  "SELECT CONCAT(DAY(R1.fecha), '/', MONTH(R1.fecha), '/', YEAR(R1.fecha)) AS periodo, SUM(R0.subtotal) AS ingreso \n" +
                    "FROM detalle_pedido R0 \n" +
                    "INNER JOIN factura R1 ON R1.numero_pedido = R0.id_pedido \n" +
                    "GROUP BY periodo", nativeQuery = true)
    List<Ingresos> getIngresosDiarios();

    @Query(value =  "SELECT CONCAT(MONTH(R1.fecha), '/', YEAR(R1.fecha)) AS periodo, SUM(R0.subtotal) AS ingreso \n" +
                    "FROM detalle_pedido R0 \n" +
                    "INNER JOIN factura R1 ON R1.numero_pedido = R0.id_pedido \n" +
                    "GROUP BY periodo", nativeQuery = true)
    List<Ingresos> getIngresosMensuales();

}
