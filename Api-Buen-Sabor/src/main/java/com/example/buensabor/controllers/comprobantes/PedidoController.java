package com.example.buensabor.controllers.comprobantes;

import com.example.buensabor.entities.comprobantes.PedidoEntity;
import com.example.buensabor.services.comprobantes.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pedidos")//ruta principal
public class PedidoController {

    private final PedidoService pedidoService;
    @Autowired

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/todos")
    public List<PedidoEntity> getPedidos() {
        return pedidoService.getPedidos();
    }

    @GetMapping("/{id}")
    public Optional<PedidoEntity> getPedidoById(@PathVariable("id") Long id) {
        return pedidoService.getPedidoById(id);
    }

    @PostMapping()
    public PedidoEntity saveOrUpdatePedido(@RequestBody PedidoEntity pedido) {
        return pedidoService.saveOrUpdatePedido(pedido);
    }
}
