package com.example.buensabor.entities.cliente;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.comprobantes.PedidoEntity;
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
public class UsuarioEntity extends EntityBean {

    @Column(name = "nombre", length = 65, nullable = false)
    @NotNull
    private String nombre;

    @Column(name = "apellido", length = 65, nullable = false)
    @NotNull
    private String apellido;

    @Column(name = "telefono", length = 65, nullable = false)
    @NotNull
    private long telefono;

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
    private Set<RolEntity> roles = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuarioEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<DomicilioEntity> domicilioEntities = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuarioEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<PedidoEntity> pedidoEntities = new ArrayList<>();
}
