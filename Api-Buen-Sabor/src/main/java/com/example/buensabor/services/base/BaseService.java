package com.example.buensabor.services.base;

import com.example.buensabor.dtos.BaseDto;
import com.example.buensabor.entities.base.BaseEntity;
import com.example.buensabor.factories.IFactory;
import com.example.buensabor.repositories.base.IBaseRepository;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BaseService<E extends BaseEntity, R extends IBaseRepository<E, ID>, DTO extends BaseDto, ID extends Serializable> implements IBaseService<E, DTO, ID> {

    protected R repository;
    protected IFactory<E, DTO> iFactory;

    public BaseService(R repository, IFactory<E, DTO> iFactory) {
        this.repository = repository;
        this.iFactory = iFactory;
    }

    public List<DTO> findAll() {
        List<E> entities = (List<E>) this.repository.findAll();
        return entities
                .stream()
                .map(iFactory::createDto)
                .collect(Collectors.toList());
    }

    public Optional<DTO> findById(ID id) {
        Optional<E> entity = this.repository.findById(id);
        return entity
                .map(iFactory::createDto)
                .stream().findFirst();
    }

    public E save(DTO dto) {
        E e = this.iFactory.createEntity(dto);
        return this.repository.save(e);
    }
}
