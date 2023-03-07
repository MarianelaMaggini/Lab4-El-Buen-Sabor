package com.example.buensabor.services.rubro;

import com.example.buensabor.dtos.RubroDto;
import com.example.buensabor.entities.rubro.Rubro;
import com.example.buensabor.factories.RubroFactory;
import com.example.buensabor.repositories.articulos.ArticuloRepository;
import com.example.buensabor.repositories.rubro.RubroRepository;
import com.example.buensabor.services.base.BaseService;
import com.example.buensabor.services.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RubroService extends BaseServiceImpl<Rubro, RubroRepository, RubroDto, Long> implements BaseService<Rubro, RubroDto, Long> {

    private final ArticuloRepository articuloRepository;

    @Autowired
    public RubroService(RubroRepository repository, RubroFactory factory, ArticuloRepository articuloRepository) {
        super(repository, factory);
        this.articuloRepository = articuloRepository;
    }


    public List<Rubro> getRubrosSinFechaDeBaja() { return repository.findByFechaBajaIsNull(); }

    public List<Rubro> getRubroByIdArticuloInsumo(Long id){
        List<Long> idsRubros = articuloRepository.getRubroIdUniqueByTipoArticuloNotInsumo(id);
        List<Rubro> rubros = repository.findByFechaBajaIsNull();
        return rubros.stream()
                .filter(rubro -> idsRubros.contains(rubro.getId()))
                .collect(Collectors.toList());
    }

}
