package com.example.buensabor.repositories.articulos;

import com.example.buensabor.entities.articulos.Inventario;
import com.example.buensabor.repositories.base.IBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInventarioRepository extends IBaseRepository<Inventario,Long> {

    List<Inventario> findInventarioByArticuloId(Long id);
}
