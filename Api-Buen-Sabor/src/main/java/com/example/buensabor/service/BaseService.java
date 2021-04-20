package com.example.buensabor.service;

import java.util.List;
import java.util.Optional;

public interface BaseService <E>{
    List<E> findByAll();
    Optional<E> findById(Long id);
    E save(E entidad);
    E update(E entidad);
    boolean delete(Long id);
}
