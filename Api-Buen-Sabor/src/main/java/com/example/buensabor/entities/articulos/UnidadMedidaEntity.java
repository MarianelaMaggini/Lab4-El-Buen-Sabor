package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    //@JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "unidadMedidaEntity")
    private List<RecetaElaboradoEntity> recetaElaboradoEntities = new ArrayList<>();

    //@JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "unidadMedidaEntity")
    private List<InventarioEntity> inventarioEntities = new ArrayList<>();

}
