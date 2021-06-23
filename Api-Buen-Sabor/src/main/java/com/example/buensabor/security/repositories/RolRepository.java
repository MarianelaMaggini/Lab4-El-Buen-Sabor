package com.example.buensabor.security.repositories;

import com.example.buensabor.security.entities.Rol;
import com.example.buensabor.security.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
