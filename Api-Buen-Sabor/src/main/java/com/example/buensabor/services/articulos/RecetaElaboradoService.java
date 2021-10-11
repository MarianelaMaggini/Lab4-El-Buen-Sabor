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

    public List<RecetaElaborado> getRecetaByIdArticulo(Long id) { return recetaElaboradoRepository.getRecetaByIdArticulo(id); }

    public List<RecetaElaborado> getRecetaHistoricaByIdArticulo(Long id) { return recetaElaboradoRepository.getRecetaHistoricaByIdArticulo(id); }

    public RecetaElaborado saveOrUpdateReceta(RecetaElaborado receta) { return (RecetaElaborado) recetaElaboradoRepository.save(receta); }

}
