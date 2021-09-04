package com.example.buensabor.entities.domicilio;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.security.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "domicilio")
public class Domicilio extends EntityBean {

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
