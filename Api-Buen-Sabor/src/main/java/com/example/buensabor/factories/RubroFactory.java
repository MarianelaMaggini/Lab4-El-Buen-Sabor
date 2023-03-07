package com.example.buensabor.factories;

import com.example.buensabor.dtos.RubroDto;
import com.example.buensabor.entities.rubro.Rubro;
import com.example.buensabor.mapper.ModelMapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RubroFactory implements IFactory<Rubro, RubroDto> {
    private final ModelMapperConfig modelMapperConfig;

    @Autowired
    public RubroFactory(ModelMapperConfig modelMapperConfig) {
        this.modelMapperConfig = modelMapperConfig;
    }

    @Override
    public Rubro createEntity(RubroDto rubroDto) {
        return modelMapperConfig.modelMapper().map(rubroDto, Rubro.class);
    }

    @Override
    public RubroDto createDto(Rubro rubro) {
        return modelMapperConfig.modelMapper().map(rubro, RubroDto.class);
    }
}
