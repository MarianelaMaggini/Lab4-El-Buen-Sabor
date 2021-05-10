package com.example.buensabor.services.articulos;

import com.example.buensabor.entities.articulos.InventarioEntity;
import com.example.buensabor.repositories.articulos.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    @Autowired
    InventarioRepository inventarioRepository;

    public List<InventarioEntity> getInventarioCompleto() {
        return (ArrayList<InventarioEntity>) inventarioRepository.findAll();
    }

    public Optional<InventarioEntity> getInventarioById(Long id) {
        return inventarioRepository.findById(id);
    }

    public InventarioEntity saveOrUpdateInventario(InventarioEntity inventario) {
        return (InventarioEntity) inventarioRepository.save(inventario);
    }

    public InventarioEntity updateInventario(InventarioEntity inventario) {
        return (InventarioEntity) inventarioRepository.save(inventario);
    }

    public boolean deleteInventarioById(Long id) {
        try {
            inventarioRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
