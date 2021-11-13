package com.example.buensabor.services.rubro;

import com.example.buensabor.entities.rubro.Rubro;
import com.example.buensabor.repositories.articulos.ArticuloRepository;
import com.example.buensabor.repositories.rubro.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RubroService {

    private final RubroRepository rubroRepository;
    private final ArticuloRepository articuloRepository;

    @Autowired
    public RubroService(RubroRepository rubroRepository, ArticuloRepository articuloRepository) {
        this.rubroRepository = rubroRepository;
        this.articuloRepository = articuloRepository;
    }

    public List<Rubro> getRubros() {
        return (ArrayList<Rubro>) rubroRepository.findAll();
    }

    public List<Rubro> getRubrosSinFechaDeBaja() { return rubroRepository.findByFechaBajaIsNull(); }

    public Optional<Rubro> getRubroById(Long id) {
        return rubroRepository.findById(id);
    }

    public Rubro saveOrUpdateRubro(Rubro rubro) {
        return rubroRepository.save(rubro);
    }

    public List<Rubro> getRubroByIdArticuloInsumo(Long id){
        List<Long> idsRubros = articuloRepository.getRubroIdUniqueByTipoArticuloNotInsumo(id);
        List<Rubro> rubros = rubroRepository.findByFechaBajaIsNull();
        return rubros.stream()
                .filter(rubro -> idsRubros.contains(rubro.getId()))
                .collect(Collectors.toList());
    }

}
