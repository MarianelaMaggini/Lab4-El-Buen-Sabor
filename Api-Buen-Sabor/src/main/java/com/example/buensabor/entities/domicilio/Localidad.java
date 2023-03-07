package com.example.buensabor.entities.domicilio;

import com.example.buensabor.entities.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "localidad")
public class Localidad extends BaseEntity {
    @Column(name = "nombre", length = 50, nullable = false)
    @NotNull
    private  String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "localidad")
    private List<Domicilio> domicilios = new ArrayList<>();
}
