package com.example.buensabor.services.articulos;

import com.example.buensabor.entities.articulos.HistoricoArticulo;
import com.example.buensabor.repositories.articulos.HistoricoArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HistoricoArticuloService {

    @Autowired
    HistoricoArticuloRepository historicoArticuloRepository;

    public List<HistoricoArticulo> getHistoricoArticulos() {
        return (List<HistoricoArticulo>) historicoArticuloRepository.findAll();
    }

    public Optional<HistoricoArticulo> getHistoricoArticuloById(Long id) {
        return historicoArticuloRepository.findById(id);
    }

    public List<HistoricoArticulo> getHistoricoArticuloByIdArticulo(Long id) {
        return (List<HistoricoArticulo>) historicoArticuloRepository.getHistoricoArticuloByIdArticulo(id);
    }

    public HistoricoArticulo saveOrUpdateHistoricoArticulo(HistoricoArticulo historicoArticulo) {
        return (HistoricoArticulo) historicoArticuloRepository.save(historicoArticulo);
    }

    public HistoricoArticulo updateHistoricoArticulo(HistoricoArticulo historicoArticulo) {
        return (HistoricoArticulo) historicoArticuloRepository.save(historicoArticulo);
    }

}
