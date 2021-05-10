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
@Table(name = "articulo_elaborado_detalle")
public class ArticuloElaboradoDetalleEntity extends EntityBean {

    @Column(name = "descripcion", length = 65)
    @NotNull
    private String descripcion;

    @Column(name = "tiempo_estimado_cocina", nullable = false)
    @NotNull
    private int tiempoEstimadoCocina;

    @JsonIgnore
    @OneToMany(mappedBy = "articuloElaboradoDetalle", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<RecetaElaboradoEntity> recetaElaboradoEntities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_articulo")
    private ArticuloEntity articuloEntity;
}
