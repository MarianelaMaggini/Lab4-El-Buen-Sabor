package com.example.buensabor.services.comprobantes;

import com.example.buensabor.entities.comprobantes.DetalleFacturaEntity;
import com.example.buensabor.repositories.comprobantes.DetalleFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DetalleFacturaService {

    @Autowired
    DetalleFacturaRepository detalleFacturaRepository;

    public List<DetalleFacturaEntity> getDetallesFactura() {
        return (ArrayList<DetalleFacturaEntity>) detalleFacturaRepository.findAll();
    }

    public Optional<DetalleFacturaEntity> getDetalleFacturaById(Long id) {
        return detalleFacturaRepository.findById(id);
    }

    public DetalleFacturaEntity saveOrUpdateDetalleFactura(DetalleFacturaEntity detalleFactura) {
        return (DetalleFacturaEntity) detalleFacturaRepository.save(detalleFactura);
    }

    public DetalleFacturaEntity updateDetalleFactura(DetalleFacturaEntity detalleFactura) {
        return (DetalleFacturaEntity) detalleFacturaRepository.save(detalleFactura);
    }

    public boolean deleteDetalleFacturaById(Long id) {
        try {
            detalleFacturaRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
