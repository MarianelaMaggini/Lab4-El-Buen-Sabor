package com.example.buensabor.controllers.comprobantes;

import com.example.buensabor.entities.comprobantes.Pedido;
import com.example.buensabor.services.comprobantes.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:59787"})
@RestController
@RequestMapping("/pedidos")//ruta principal
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping("/todos")
    public List<Pedido> getPedidos() {
        return pedidoService.getPedidos();
    }

    @GetMapping("/{id}")
    public Optional<Pedido> getPedidoById(@PathVariable("id") Long id) {
        return pedidoService.getPedidoById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public Pedido saveOrUpdatePedido(@RequestBody Pedido pedido) {
        return pedidoService.saveOrUpdatePedido(pedido);
    }
}
