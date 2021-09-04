package com.example.buensabor.services.comprobantes;

import com.example.buensabor.entities.comprobantes.Pedido;
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

    public List<Pedido> getPedidos() {
        return (ArrayList<Pedido>) pedidoRepository.findAll();
    }

    public Optional<Pedido> getPedidoById(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido saveOrUpdatePedido(Pedido pedido) {
        return (Pedido) pedidoRepository.save(pedido);
    }

    public Pedido updatePedido(Pedido pedido) {
        return (Pedido) pedidoRepository.save(pedido);
    }

    public List<Pedido> getPedidosByUsuarioId(Long idUsuario) {
        return pedidoRepository.findByIdUsuario(idUsuario);
    }
}
