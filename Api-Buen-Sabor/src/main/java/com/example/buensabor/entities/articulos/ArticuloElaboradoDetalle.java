package com.example.buensabor.entities.articulos;


import com.example.buensabor.entities.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "articulo_elaborado_detalle")
public class ArticuloElaboradoDetalle extends BaseEntity {

    @Column(name = "descripcion", length = 65, nullable = false)
    @NotNull
    private String descripcion;

    @Column(name = "tiempo_estimado_cocina", nullable = false)
    @NotNull
    private int tiempoEstimadoCocina;

    @JsonIgnore
    @OneToMany(mappedBy = "articuloElaboradoDetalle")
    private List<RecetaElaborado> recetaElaborado = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;
}
