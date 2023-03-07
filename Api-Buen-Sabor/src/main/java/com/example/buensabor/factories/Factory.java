package com.example.buensabor.factories;

public interface Factory<E, DTO>{
    E createEntity (DTO dto);
    DTO createDto (E entity);
}
