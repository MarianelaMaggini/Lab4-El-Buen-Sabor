package com.example.buensabor.services.rubro;

import com.example.buensabor.dtos.RubroDto;
import com.example.buensabor.entities.rubro.Rubro;
import com.example.buensabor.factories.RubroFactory;
import com.example.buensabor.repositories.articulos.IArticuloRepository;
import com.example.buensabor.repositories.rubro.IRubroRepository;
import com.example.buensabor.services.base.BaseService;
import com.example.buensabor.services.base.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RubroService extends BaseService<Rubro, IRubroRepository, RubroDto, Long> implements IBaseService<Rubro, RubroDto, Long> {

    private final IArticuloRepository articuloRepository;

    @Autowired
    public RubroService(IRubroRepository iRubroRepository, RubroFactory rubroFactory, IArticuloRepository articuloRepository) {
        super(iRubroRepository, rubroFactory);
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
