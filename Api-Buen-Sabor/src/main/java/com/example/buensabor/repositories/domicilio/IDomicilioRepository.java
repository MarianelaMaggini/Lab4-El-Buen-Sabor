package com.example.buensabor.repositories.domicilio;

import com.example.buensabor.entities.domicilio.Domicilio;
import com.example.buensabor.repositories.base.IBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDomicilioRepository extends IBaseRepository<Domicilio,Long> {
    List<Domicilio> findByFechaBajaIsNull();

    @Query(value = "SELECT d FROM Domicilio d WHERE d.usuario.id = :idUsuario AND d.fechaBaja IS null")
    List<Domicilio> findByIdUsuario(@Param("idUsuario") Long idUsuario);

    @Query(value = "SELECT d FROM Domicilio d WHERE d.localidad.id = :idLocalidad")
    List<Domicilio> findByIdLocalidad(@Param("idLocalidad") Long idLocalidad);
}
