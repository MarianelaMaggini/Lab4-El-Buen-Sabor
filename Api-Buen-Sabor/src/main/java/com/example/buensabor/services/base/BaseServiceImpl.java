package com.example.buensabor.services.base;

import com.example.buensabor.dtos.BaseDto;
import com.example.buensabor.entities.base.BaseEntity;
import com.example.buensabor.factories.Factory;
import com.example.buensabor.repositories.base.BaseRepository;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BaseServiceImpl<E extends BaseEntity, R extends BaseRepository<E, ID>, DTO extends BaseDto, ID extends Serializable> implements BaseService<E, DTO, ID> {

    protected R repository;
    protected Factory<E, DTO> factory;

    public BaseServiceImpl(R repository, Factory<E, DTO> factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public List<DTO> findAll() {
        List<E> entities = (List<E>) this.repository.findAll();
        return entities
                .stream()
                .map(factory::createDto)
                .collect(Collectors.toList());
    }

    public Optional<DTO> findById(ID id) {
        Optional<E> entity = this.repository.findById(id);
        return entity
                .map(factory::createDto)
                .stream().findFirst();
    }

    public E save(DTO dto) {
        E e = this.factory.createEntity(dto);
        return this.repository.save(e);
    }
}
