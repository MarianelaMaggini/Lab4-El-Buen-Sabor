package com.example.buensabor.entities.domicilio;

import com.example.buensabor.entities.base.BaseEntity;
import com.example.buensabor.security.entities.Usuario;
import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "domicilio")
public class Domicilio extends BaseEntity {

    @Column(name = "calle", length = 150, nullable = false)
    @NotNull
    private  String calle;

    @Column(name = "numero", nullable = false)
    @NotNull
    private int numero;

    @Column(name = "fecha_baja")
    private Date fechaBaja;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_localidad")
    private Localidad localidad;
}
