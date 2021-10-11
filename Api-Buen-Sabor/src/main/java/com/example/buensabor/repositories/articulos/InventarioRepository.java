package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.Inventario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioRepository extends CrudRepository <Inventario,Long>{

    List<Inventario> findInventarioByArticuloId(Long id);
}
