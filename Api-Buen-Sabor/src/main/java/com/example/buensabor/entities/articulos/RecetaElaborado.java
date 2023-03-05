package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.base.BaseEntity;
import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "receta_elaborado")
public class RecetaElaborado extends BaseEntity {

    @Column(name = "cantidad", nullable = false)
    @NotNull
    private double cantidad;

    @Column(name = "fecha_baja")
    private Date fechaBaja;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_unidad_medida")
    private UnidadMedida unidadMedida;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_articulo_elaborado_detalle")
    private ArticuloElaboradoDetalle articuloElaboradoDetalle;

}
