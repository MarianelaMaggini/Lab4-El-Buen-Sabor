package com.example.buensabor.controllers.comprobantes;

import com.example.buensabor.entities.comprobantes.DetalleFacturaEntity;
import com.example.buensabor.services.comprobantes.DetalleFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/detallesFactura")//ruta principal
public class DetalleFacturaController {

    private final DetalleFacturaService detalleFacturaService;

    @Autowired
    public DetalleFacturaController(DetalleFacturaService detalleFacturaService) {
        this.detalleFacturaService = detalleFacturaService;
    }

    @GetMapping("/todos")
    public List<DetalleFacturaEntity> getDetallesFactura() {
        return detalleFacturaService.getDetallesFactura();
    }

    @GetMapping("/{id}")
    public Optional<DetalleFacturaEntity> getDetalleFacturaById(@PathVariable("id") Long id) {
        return detalleFacturaService.getDetalleFacturaById(id);
    }

    @PostMapping()
    public DetalleFacturaEntity saveOrUpdateDetalleFactura(@RequestBody DetalleFacturaEntity detalleFactura) {
        return detalleFacturaService.saveOrUpdateDetalleFactura(detalleFactura);
    }
}
