package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "unidad_medida")
public class UnidadMedida extends EntityBean {

    @Column(name = "denominacion")
    @NotNull
    private String denominacion;

    @JsonIgnore
    @OneToMany(mappedBy = "unidadMedida", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<RecetaElaborado> recetaElaborados = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "unidadMedida", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Inventario> inventarios = new ArrayList<>();

}