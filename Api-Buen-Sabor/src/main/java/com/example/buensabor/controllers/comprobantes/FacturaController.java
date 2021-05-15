package com.example.buensabor.controllers.comprobantes;

import com.example.buensabor.entities.comprobantes.FacturaEntity;
import com.example.buensabor.services.comprobantes.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/facturas")//ruta principal
public class FacturaController {

    @Autowired
    FacturaService facturaService;

    @GetMapping("/todos")
    public List<FacturaEntity> getFacturas() {
        return facturaService.getFacturas();
    }

    @GetMapping("/{id}")
    public Optional<FacturaEntity> getFacturaById(@PathVariable("id") Long id) {
        return facturaService.getFacturaById(id);
    }

    @PostMapping()
    public FacturaEntity saveOrUpdateFactura(@RequestBody FacturaEntity factura) {
        return facturaService.saveOrUpdateFactura(factura);
    }
}
