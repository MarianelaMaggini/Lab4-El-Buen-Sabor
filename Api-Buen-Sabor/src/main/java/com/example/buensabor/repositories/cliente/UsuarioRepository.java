package com.example.buensabor.repositories.cliente;

import com.example.buensabor.entities.articulos.ArticuloEntity;
import com.example.buensabor.entities.cliente.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository <UsuarioEntity,Long>{
}
