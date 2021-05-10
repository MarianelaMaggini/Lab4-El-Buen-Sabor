package com.example.buensabor.services.comprobantes;

import com.example.buensabor.entities.comprobantes.DetallePedidoEntity;
import com.example.buensabor.repositories.comprobantes.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService {

    @Autowired
    DetallePedidoRepository detallePedidoRepository;

    public List<DetallePedidoEntity> getDetallesPedido() {
        return (ArrayList<DetallePedidoEntity>) detallePedidoRepository.findAll();
    }

    public Optional<DetallePedidoEntity> getDetallePedidoById(Long id) {
        return detallePedidoRepository.findById(id);
    }

    public DetallePedidoEntity saveOrUpdateDetallePedido(DetallePedidoEntity detallePedido) {
        return (DetallePedidoEntity) detallePedidoRepository.save(detallePedido);
    }

    public DetallePedidoEntity updateDetallePedido(DetallePedidoEntity detallePedido) {
        return (DetallePedidoEntity) detallePedidoRepository.save(detallePedido);
    }

    public boolean deleteDetallePedidoById(Long id) {
        try {
            detallePedidoRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
