package com.example.buensabor.controllers.comprobantes;

import com.example.buensabor.entities.comprobantes.Factura;
import com.example.buensabor.services.comprobantes.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:59787"})
@RestController
@RequestMapping("/facturas") //ruta principal
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public Factura saveOrUpdateFactura(@RequestBody Factura factura) {
        return facturaService.saveOrUpdateFactura(factura);
    }

}
