package com.example.buensabor.services.articulos;

import com.example.buensabor.entities.articulos.HistoricoArticulo;
import com.example.buensabor.repositories.articulos.HistoricoArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HistoricoArticuloService {

    @Autowired
    HistoricoArticuloRepository historicoArticuloRepository;

    public List<HistoricoArticulo> getHistoricoArticulos() {
        return (ArrayList<HistoricoArticulo>) historicoArticuloRepository.findAll();
    }

    public Optional<HistoricoArticulo> getHistoricoArticuloById(Long id) {
        return historicoArticuloRepository.findById(id);
    }

    public HistoricoArticulo saveOrUpdateHistoricoArticulo(HistoricoArticulo historicoArticulo) {
        return (HistoricoArticulo) historicoArticuloRepository.save(historicoArticulo);
    }

    public HistoricoArticulo updateHistoricoArticulo(HistoricoArticulo historicoArticulo) {
        return (HistoricoArticulo) historicoArticuloRepository.save(historicoArticulo);
    }

    public boolean deleteHistoricoArticuloById(Long id) {
        try {
            historicoArticuloRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Double> getPrecioCompra(){
        List<Double> preciosVentas = new ArrayList<>();
        for (Double precioCompra: historicoArticuloRepository.getPrecioCompra()){
            preciosVentas.add(precioCompra * 1.5);
        }
        return preciosVentas;
    }
}
