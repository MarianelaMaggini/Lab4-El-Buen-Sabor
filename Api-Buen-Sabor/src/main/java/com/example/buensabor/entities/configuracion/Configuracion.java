package com.example.buensabor.entities.configuracion;

import com.example.buensabor.entities.base.BaseEntity;
import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "configuracion")
public class Configuracion extends BaseEntity {

    @Column(name = "cantidad_cocineros", nullable = false)
    @NotNull
    private int cantidadCocineros;

    @Column(name = "email_empresa", length = 60, nullable = false)
    @Email
    private String emailEmpresa;

    @Column(name = "token_mercado_pago", length = 150, nullable = false)
    @NotNull
    private String tokenMercadoPago;
}


