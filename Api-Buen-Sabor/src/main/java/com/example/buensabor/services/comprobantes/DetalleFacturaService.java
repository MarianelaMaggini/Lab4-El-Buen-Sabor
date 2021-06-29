package com.example.buensabor.services.comprobantes;

import com.example.buensabor.entities.comprobantes.DetalleFactura;
import com.example.buensabor.entities.comprobantes.DetallePedido;
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

    public List<DetalleFactura> getDetallesFactura() {
        return (ArrayList<DetalleFactura>) detalleFacturaRepository.findAll();
    }

    public Optional<DetalleFactura> getDetalleFacturaById(Long id) {
        return detalleFacturaRepository.findById(id);
    }

    public List<DetalleFactura> getDetalleByIdFactura(Long id) {
        return (ArrayList<DetalleFactura>) detalleFacturaRepository.getDetalleByIdFactura(id);
    }

    public DetalleFactura saveOrUpdateDetalleFactura(DetalleFactura detalleFactura) {
        return (DetalleFactura) detalleFacturaRepository.save(detalleFactura);
    }

    public DetalleFactura updateDetalleFactura(DetalleFactura detalleFactura) {
        return (DetalleFactura) detalleFacturaRepository.save(detalleFactura);
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
