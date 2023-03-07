package com.example.buensabor.services.base;

import com.example.buensabor.dtos.BaseDto;
import com.example.buensabor.entities.base.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<E extends BaseEntity, DTO extends BaseDto, ID extends Serializable> {
    List<DTO> findAll();
    Optional<DTO> findById(ID id);
    E save (DTO dto);
}
