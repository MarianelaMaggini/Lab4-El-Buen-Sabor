package com.example.buensabor.services.articulos;

import com.example.buensabor.entities.articulos.RecetaElaborado;
import com.example.buensabor.repositories.articulos.RecetaElaboradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecetaElaboradoService {

    @Autowired
    RecetaElaboradoRepository recetaElaboradoRepository;

    public List<RecetaElaborado> getRecetas() { return (ArrayList<RecetaElaborado>) recetaElaboradoRepository.findAll(); }

    public Optional<RecetaElaborado> getRecetaById(Long id) { return recetaElaboradoRepository.findById(id); }

    public List<RecetaElaborado> getRecetaByIdAed(Long id) { return recetaElaboradoRepository.getRecetaByIdAed(id); }

    public List<RecetaElaborado> getRecetaHistoricaByIdAed(Long id) { return recetaElaboradoRepository.getRecetaHistoricaByIdAed(id); }

    public RecetaElaborado saveOrUpdateReceta(RecetaElaborado receta) { return (RecetaElaborado) recetaElaboradoRepository.save(receta); }

}
