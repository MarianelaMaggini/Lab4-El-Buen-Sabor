package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.EntityBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventario")
public class Inventario extends EntityBean {

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
