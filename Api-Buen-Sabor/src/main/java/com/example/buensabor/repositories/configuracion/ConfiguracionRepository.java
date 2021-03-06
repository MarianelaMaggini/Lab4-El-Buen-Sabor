package com.example.buensabor.repositories.configuracion;

import com.example.buensabor.entities.configuracion.Configuracion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracionRepository extends CrudRepository<Configuracion,Long> {

    @Query(value = "SELECT token_mercado_pago FROM configuracion", nativeQuery = true)
    String getTokenMercadoPago();

    @Query(value = "SELECT R0.cantidad_cocineros FROM configuracion R0", nativeQuery = true)
    Integer getCantidadCocineros();

}
