package com.example.buensabor.services.comprobantes;

import com.example.buensabor.entities.comprobantes.DetallePedido;
import com.example.buensabor.repositories.comprobantes.IDetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService {

    @Autowired
    IDetallePedidoRepository detallePedidoRepository;

    public List<DetallePedido> getDetallesPedido() {
        return (ArrayList<DetallePedido>) detallePedidoRepository.findAll();
    }

    public Optional<DetallePedido> getDetallePedidoById(Long id) {
        return detallePedidoRepository.findById(id);
    }

    public List<DetallePedido> getDetalleByIdPedido(Long id) {
        return (ArrayList<DetallePedido>) detallePedidoRepository.getDetalleByIdPedido(id);
    }

    public DetallePedido saveOrUpdateDetallePedido(DetallePedido detallePedido) {
        return (DetallePedido) detallePedidoRepository.save(detallePedido);
    }

    public DetallePedido updateDetallePedido(DetallePedido detallePedido) {
        return (DetallePedido) detallePedidoRepository.save(detallePedido);
    }

}
