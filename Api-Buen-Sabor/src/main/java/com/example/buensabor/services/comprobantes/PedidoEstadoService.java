package com.example.buensabor.services.comprobantes;

import com.example.buensabor.entities.comprobantes.PedidoEstado;
import com.example.buensabor.repositories.comprobantes.IPedidoEstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoEstadoService {

    @Autowired
    IPedidoEstadoRepository pedidoEstadoRepository;

    public List<PedidoEstado> getPedidoEstados() {
        return (ArrayList<PedidoEstado>) pedidoEstadoRepository.findAll();
    }

    public Optional<PedidoEstado> getPedidoEstadoById(Long id) {
        return pedidoEstadoRepository.findById(id);
    }

}
