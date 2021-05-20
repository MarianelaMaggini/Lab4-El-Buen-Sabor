package com.example.buensabor.controllers.comprobantes;

import com.example.buensabor.entities.comprobantes.DetallePedidoEntity;
import com.example.buensabor.services.comprobantes.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/detallesPedido")//ruta principal
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    @Autowired
    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping("/todos")
    public List<DetallePedidoEntity> getDetallesPedido() {return detallePedidoService.getDetallesPedido();}

    @GetMapping("/{id}")
    public Optional<DetallePedidoEntity> getDetallePedidoById(@PathVariable("id") Long id) {
        return detallePedidoService.getDetallePedidoById(id);
    }

    @PostMapping()
    public DetallePedidoEntity saveOrUpdateDetallePedido(@RequestBody DetallePedidoEntity detallePedido) {
        return detallePedidoService.saveOrUpdateDetallePedido(detallePedido);
    }
}
