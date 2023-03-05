package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "inventario")
public class Inventario extends BaseEntity {

    @Column(name = "stock_actual", nullable = false)
    @NotNull
    private double stockActual;

    @Column(name = "stock_minimo", nullable = false)
    @NotNull
    private double stockMinimo;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

}
