package com.example.buensabor.services.cliente;

import com.example.buensabor.entities.cliente.RolEntity;
import com.example.buensabor.repositories.cliente.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public List<RolEntity> getRoles() {
        return (ArrayList<RolEntity>) rolRepository.findAll();
    }

    public Optional<RolEntity> getRolById(Long id) {
        return rolRepository.findById(id);
    }

    public RolEntity saveOrUpdateRol(RolEntity rol) {
        return (RolEntity) rolRepository.save(rol);
    }

    public RolEntity updateRol(RolEntity rol) {
        return (RolEntity) rolRepository.save(rol);
    }

    public boolean deleteRolById(Long id) {
        try {
            rolRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
