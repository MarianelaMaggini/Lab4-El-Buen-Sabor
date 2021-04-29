package com.example.buensabor.entities.cliente;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.comprobantes.Pedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario extends EntityBean {

    @Column(name = "nombre", length = 65)
    @NotNull
    private String nombre;

    @Column(name = "apellido", length = 65)
    @NotNull
    private String apellido;

    @Column(name = "telefono", length = 65)
    @NotNull
    private long telefono;

    @Column(name = "email", length = 65)
    @NotNull
    @Email
    private String email;

    @Column(name = "clave", length = 65)
    @NotNull
    private String clave;

    @Column(name = "rol", length = 65)
    @NotNull
    private String rol;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Domicilio> domicilios = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Pedido>pedidos = new ArrayList<>();
}
