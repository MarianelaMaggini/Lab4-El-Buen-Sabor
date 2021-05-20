package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "receta_elaborado")
public class RecetaElaboradoEntity extends EntityBean {

    @Column(name = "cantidad", nullable = false)
    @NotNull
    private double cantidad;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_unidad_medida")
    private UnidadMedidaEntity unidadMedidaEntity;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_articulo")
    private ArticuloEntity articuloEntity;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_articulo_elaborado_detalle")
    private ArticuloElaboradoDetalleEntity articuloElaboradoDetalleEntity;
}
