package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "receta_elaborado")
public class RecetaElaborado extends EntityBean {

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
