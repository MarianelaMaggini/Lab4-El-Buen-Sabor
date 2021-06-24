package com.example.buensabor.security.entities;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.comprobantes.Pedido;
import com.example.buensabor.entities.domicilio.Domicilio;
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
@Table(name = "usuario")
public class Usuario extends EntityBean {

    @Column(name = "nombre", length = 65, nullable = false)
    @NotNull
    private String nombre;

    @Column(name = "apellido", length = 65, nullable = false)
    @NotNull
    private String apellido;

    @Column(name = "telefono", length = 65, nullable = false)
    @NotNull
    private String telefono;

    @Column(name = "email", length = 65, nullable = false)
    @NotNull
    @Email
    private String email;

    @Column(name = "clave", length = 150, nullable = false)
    @NotNull
    private String clave;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    @NotNull
    private Set<Rol> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Domicilio> domicilios = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos = new ArrayList<>();

    public Usuario() { }

    public Usuario(@NotNull String nombre, @NotNull String apellido, String telefono, String email, @NotNull String clave) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.clave = clave;
    }
}
