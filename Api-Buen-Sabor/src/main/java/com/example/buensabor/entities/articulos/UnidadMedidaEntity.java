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
public class UnidadMedidaEntity extends EntityBean {

    @Column(name = "denominacion", nullable = false)
    @NotNull
    private String denominacion;

    @JsonIgnore
    @OneToMany(mappedBy = "unidadMedidaEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<RecetaElaboradoEntity> recetaElaboradoEntities = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "unidadMedidaEntity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<InventarioEntity> inventarioEntities = new ArrayList<>();

}
