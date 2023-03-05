package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.Inventario;
import com.example.buensabor.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioRepository extends BaseRepository<Inventario,Long> {

    List<Inventario> findInventarioByArticuloId(Long id);
}
