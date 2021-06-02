package com.example.buensabor.services.cliente;

import com.example.buensabor.entities.cliente.Rol;
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

    public List<Rol> getRoles() {
        return (ArrayList<Rol>) rolRepository.findAll();
    }

    public Optional<Rol> getRolById(Long id) {
        return rolRepository.findById(id);
    }

    public Rol saveOrUpdateRol(Rol rol) {
        return (Rol) rolRepository.save(rol);
    }

    public Rol updateRol(Rol rol) {
        return (Rol) rolRepository.save(rol);
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
