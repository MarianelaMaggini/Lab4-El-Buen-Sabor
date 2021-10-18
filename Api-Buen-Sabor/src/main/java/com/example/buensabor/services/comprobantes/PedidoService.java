package com.example.buensabor.services.comprobantes;

import com.example.buensabor.entities.comprobantes.Pedido;
import com.example.buensabor.entities.comprobantes.DetallePedido;
import com.example.buensabor.entities.comprobantes.PedidoEstado;
import com.example.buensabor.repositories.articulos.ArticuloElaboradoDetalleRepository;
import com.example.buensabor.repositories.comprobantes.DetallePedidoRepository;
import com.example.buensabor.repositories.comprobantes.PedidoEstadoRepository;
import com.example.buensabor.repositories.comprobantes.PedidoRepository;
import com.example.buensabor.repositories.configuracion.ConfiguracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    PedidoEstadoService pedidoEstadoService;

    @Autowired
    DetallePedidoRepository detallePedidoRepository;

    @Autowired
    ArticuloElaboradoDetalleRepository articuloElaboradoDetalleRepository;

    @Autowired
    ConfiguracionRepository configuracionRepository;

    public List<Pedido> getPedidos() {
        return (ArrayList<Pedido>) pedidoRepository.findAll();
    }

    public Optional<Pedido> getPedidoById(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido saveOrUpdatePedido(Pedido pedido) {
        if(pedido.getPedidoEstado().getId() == 1) {
            double sumatoria1 = 0;
            double sumatoria2 = 0;
            double tiempoFinal = 0;
            Date horaEstimadaFin = pedido.getHoraEstimadaFin();
            List<DetallePedido> detallePedido = detallePedidoRepository.getDetalleByIdPedido(pedido.getNumeroPedido());
            for (DetallePedido detalle : detallePedido) {
                sumatoria1 += (articuloElaboradoDetalleRepository.getArticuloDetalleByIdArticulo(detalle.getArticulo().getId())).get().getTiempoEstimadoCocina();
            }
            List<Pedido> pedidosEP = pedidoRepository.findPedidoByPedidoEstadoId(2);
            for (Pedido pedidoEP : pedidosEP) {
                List<DetallePedido> detallePedidoEP = detallePedidoRepository.getDetalleByIdPedido(pedidoEP.getNumeroPedido());
                for (DetallePedido detalleEP : detallePedidoEP) {
                    sumatoria2 += (articuloElaboradoDetalleRepository.getArticuloDetalleByIdArticulo(detalleEP.getArticulo().getId())).get().getTiempoEstimadoCocina();
                }
            }
            sumatoria2 = sumatoria2 / (configuracionRepository.getCantidadCocineros());
            if(pedido.getTipoEnvio().getId() == 2) {
                tiempoFinal = sumatoria1 + sumatoria2 + 10;
            } else {
                tiempoFinal = sumatoria1 + sumatoria2;
            }
            horaEstimadaFin.setMinutes(horaEstimadaFin.getMinutes() + (int) tiempoFinal);
            pedido.setHoraEstimadaFin(horaEstimadaFin);
        }
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> getPedidosByUsuarioId(Long idUsuario) {
        return pedidoRepository.findByIdUsuario(idUsuario);
    }

}
