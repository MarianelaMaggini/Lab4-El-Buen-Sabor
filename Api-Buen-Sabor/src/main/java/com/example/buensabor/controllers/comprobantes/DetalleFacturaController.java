package com.example.buensabor.controllers.comprobantes;

import com.example.buensabor.entities.comprobantes.DetalleFactura;
import com.example.buensabor.services.comprobantes.DetalleFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:59787"})
@RestController
@RequestMapping("/detallesFactura") //ruta principal
public class DetalleFacturaController {

    private final DetalleFacturaService detalleFacturaService;

    @Autowired
    public DetalleFacturaController(DetalleFacturaService detalleFacturaService) {
        this.detalleFacturaService = detalleFacturaService;
    }

    @GetMapping("/todos")
    public List<DetalleFactura> getDetallesFactura() {
        return detalleFacturaService.getDetallesFactura();
    }

    @GetMapping("/{id}")
    public Optional<DetalleFactura> getDetalleFacturaById(@PathVariable("id") Long id) {
        return detalleFacturaService.getDetalleFacturaById(id);
    }

    @GetMapping("/factura")
    public List<DetalleFactura> getDetalleByIdFactura(@RequestParam("id") Long id) {
        return detalleFacturaService.getDetalleByIdFactura(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public DetalleFactura saveOrUpdateDetalleFactura(@RequestBody DetalleFactura detalleFactura) {
        return detalleFacturaService.saveOrUpdateDetalleFactura(detalleFactura);
    }
}
