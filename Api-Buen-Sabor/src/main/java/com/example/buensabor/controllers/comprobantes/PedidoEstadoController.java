package com.example.buensabor.controllers.comprobantes;

import com.example.buensabor.entities.comprobantes.PedidoEstado;
import com.example.buensabor.services.comprobantes.PedidoEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidoEstados") // Ruta principal
public class PedidoEstadoController {

    @Autowired
    PedidoEstadoService pedidoEstadoService;

    @GetMapping("/todos")
    public List<PedidoEstado> getPedidoEstados() {
        return pedidoEstadoService.getPedidoEstados();
    }

    @GetMapping("/{id}")
    public Optional<PedidoEstado> getPedidoEstadoById(@PathVariable("id") Long id) {
        return pedidoEstadoService.getPedidoEstadoById(id);
    }

}
