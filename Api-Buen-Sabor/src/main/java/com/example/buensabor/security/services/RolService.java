package com.example.buensabor.security.services;

import com.example.buensabor.security.entities.Rol;
import com.example.buensabor.security.enums.RolNombre;
import com.example.buensabor.security.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    private final RolRepository rolRepository;

    @Autowired
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return this.rolRepository.findByRolNombre(rolNombre);
    }
}

