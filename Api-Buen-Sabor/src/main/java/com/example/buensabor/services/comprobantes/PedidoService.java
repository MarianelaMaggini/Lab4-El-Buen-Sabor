package com.example.buensabor.services.comprobantes;

import com.example.buensabor.entities.comprobantes.PedidoEntity;
import com.example.buensabor.repositories.comprobantes.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public List<PedidoEntity> getPedidos() {
        return (ArrayList<PedidoEntity>) pedidoRepository.findAll();
    }

    public Optional<PedidoEntity> getPedidoById(Long id) {
        return pedidoRepository.findById(id);
    }

    public PedidoEntity saveOrUpdatePedido(PedidoEntity pedido) {
        return (PedidoEntity) pedidoRepository.save(pedido);
    }

    public PedidoEntity updatePedido(PedidoEntity pedido) {
        return (PedidoEntity) pedidoRepository.save(pedido);
    }

    public boolean deletePedidoById(Long id) {
        try {
            pedidoRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
