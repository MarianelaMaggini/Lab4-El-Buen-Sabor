package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.rubros.Rubro;
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
public class RecetaElaborado extends EntityBean {

    @Column(name = "cantidad", nullable = false)
    @NotNull
    private double cantidad;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_unidad_medida")
    private UnidadMedida unidadMedida;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_articulo_elaborado_detalle")
    private ArticuloElaboradoDetalle articuloElaboradoDetalle;
}
