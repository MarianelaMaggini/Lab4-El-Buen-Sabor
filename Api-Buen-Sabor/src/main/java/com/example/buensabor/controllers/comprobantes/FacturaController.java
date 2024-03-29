package com.example.buensabor.controllers.comprobantes;

import com.example.buensabor.entities.comprobantes.Factura;
import com.example.buensabor.services.comprobantes.FacturaService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/facturas") // Ruta principal
public class FacturaController {

    private final FacturaService facturaService;

    @Autowired
    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping("/todos")
    public List<Factura> getFacturas() {
        return facturaService.getFacturas();
    }

    @GetMapping("/{id}")
    public Optional<Factura> getFacturaById(@PathVariable("id") Long id) {
        return facturaService.getFacturaById(id);
    }

    @GetMapping("/pedido")
    public Optional<Factura> getFacturaByPedidoNumeroPedido(@RequestParam("numero") Long numero) {
        return facturaService.getFacturaByPedidoNumeroPedido(numero);
    }

    @PostMapping()
    public Factura saveOrUpdateFactura(@RequestBody Factura factura) throws IOException, DocumentException, MessagingException {
        return facturaService.saveOrUpdateFactura(factura);
    }

}
