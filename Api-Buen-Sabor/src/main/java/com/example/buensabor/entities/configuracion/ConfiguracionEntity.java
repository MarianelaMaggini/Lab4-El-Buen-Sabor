package com.example.buensabor.entities.configuracion;

import com.example.buensabor.entities.EntityBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "configuracion")
public class ConfiguracionEntity extends EntityBean {

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
