package com.example.buensabor.repositories.base;

import com.example.buensabor.entities.base.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity, ID extends Serializable> extends CrudRepository<E, ID> {
}
