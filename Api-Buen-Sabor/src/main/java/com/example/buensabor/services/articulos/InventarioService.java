package com.example.buensabor.services.articulos;

import com.example.buensabor.entities.articulos.Inventario;
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

    public List<Inventario> getInventarioCompleto() {
        return (ArrayList<Inventario>) inventarioRepository.findAll();
    }

    public Optional<Inventario> getInventarioById(Long id) {
        return inventarioRepository.findById(id);
    }

    public Inventario saveOrUpdateInventario(Inventario inventario) {
        return (Inventario) inventarioRepository.save(inventario);
    }

    public Inventario updateInventario(Inventario inventario) {
        return (Inventario) inventarioRepository.save(inventario);
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
