package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "unidad_medida")
public class UnidadMedida extends BaseEntity {

    @Column(name = "denominacion", nullable = false)
    @NotNull
    private String denominacion;

    @JsonIgnore
    @OneToMany(mappedBy = "unidadMedida")
    private List<RecetaElaborado> recetaElaborados = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "unidadMedida")
    private List<Articulo> articulos;

}
