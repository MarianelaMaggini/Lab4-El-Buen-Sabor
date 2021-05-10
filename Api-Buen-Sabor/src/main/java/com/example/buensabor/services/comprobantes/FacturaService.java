package com.example.buensabor.services.comprobantes;

import com.example.buensabor.entities.comprobantes.FacturaEntity;
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

    public List<FacturaEntity> getFacturas() {
        return (ArrayList<FacturaEntity>) facturaRepository.findAll();
    }

    public Optional<FacturaEntity> getFacturaById(Long id) {
        return facturaRepository.findById(id);
    }

    public FacturaEntity saveOrUpdateFactura(FacturaEntity factura) {
        return (FacturaEntity) facturaRepository.save(factura);
    }

    public FacturaEntity updateFactura(FacturaEntity factura) {
        return (FacturaEntity) facturaRepository.save(factura);
    }

    public boolean deleteFacturaById(Long id) {
        try {
            facturaRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
