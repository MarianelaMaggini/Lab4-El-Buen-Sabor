package com.example.buensabor.entities.cliente;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.comprobantes.Pedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    @NotNull
    private Set<Rol> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Domicilio> domicilios = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Pedido>pedidos = new ArrayList<>();
}
