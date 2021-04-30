package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "articulo_manufacturado_detalle")
public class ArticuloManufacturadoDetalle extends EntityBean {

    @Column(name = "cantidad")
    @NotNull
    private double cantidad;

    @Column(name = "unidad_medida")
    @NotNull
    private String unidadMedida;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_art_insumo")
    private ArticuloInsumo articuloInsumo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_art_manufacturado")
    private ArticuloManufacturado articuloManufacturado;
}
