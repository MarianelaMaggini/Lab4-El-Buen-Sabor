package com.example.buensabor.services.comprobantes;

import com.example.buensabor.entities.comprobantes.TipoEnvio;
import com.example.buensabor.repositories.comprobantes.TipoEnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TipoEnvioService {

    @Autowired
    TipoEnvioRepository tipoEnvioRepository;

    public List<TipoEnvio> getTiposEnvio() {
        return (ArrayList<TipoEnvio>) tipoEnvioRepository.findAll();
    }

    public Optional<TipoEnvio> getTipoEnvioById(Long id) {
        return tipoEnvioRepository.findById(id);
    }

}
