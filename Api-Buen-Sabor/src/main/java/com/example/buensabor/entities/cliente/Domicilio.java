package com.example.buensabor.entities.cliente;

import com.example.buensabor.entities.EntityBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "domicilio")
public class Domicilio extends EntityBean {

    @Column(name = "calle", length = 150)
    @NotNull
    private  String calle;

    @Column(name = "numero")
    @NotNull
    private int numero;

    @Column(name = "localidad")
    @NotNull
    private String localidad;

    @ManyToOne(fetch = FetchType.LAZY,cascade ={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}