package com.example.buensabor.services.comprobantes;

import com.example.buensabor.entities.comprobantes.Factura;
import com.example.buensabor.repositories.comprobantes.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    FacturaRepository facturaRepository;

    public List<Factura> getFacturas() {
        return (ArrayList<Factura>) facturaRepository.findAll();
    }

    public Optional<Factura> getFacturaById(Long id) {
        return facturaRepository.findById(id);
    }

    public Factura saveOrUpdateFactura(Factura factura) {
        return (Factura) facturaRepository.save(factura);
    }

    public Factura updateFactura(Factura factura) {
        return (Factura) facturaRepository.save(factura);
    }

}
