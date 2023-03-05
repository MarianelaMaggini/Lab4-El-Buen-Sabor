package com.example.buensabor.factories;

public interface IFactory <E, DTO>{
    E createEntity (DTO dto);
    DTO createDto (E entity);
}
