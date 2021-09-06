package com.example.buensabor.controllers.comprobantes;

import com.example.buensabor.entities.comprobantes.DetallePedido;
import com.example.buensabor.services.comprobantes.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
@RestController
@RequestMapping("/detallesPedido")//ruta principal
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    @Autowired
    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping("/todos")
    public List<DetallePedido> getDetallesPedido() {return detallePedidoService.getDetallesPedido();}

    @GetMapping("/{id}")
    public Optional<DetallePedido> getDetallePedidoById(@PathVariable("id") Long id) {
        return detallePedidoService.getDetallePedidoById(id);
    }

    @GetMapping("/pedido")
    public List<DetallePedido> getDetalleByIdPedido(@RequestParam("id") Long id) {
        return detallePedidoService.getDetalleByIdPedido(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public DetallePedido saveOrUpdateDetallePedido(@RequestBody DetallePedido detallePedido) {
        return detallePedidoService.saveOrUpdateDetallePedido(detallePedido);
    }

}
