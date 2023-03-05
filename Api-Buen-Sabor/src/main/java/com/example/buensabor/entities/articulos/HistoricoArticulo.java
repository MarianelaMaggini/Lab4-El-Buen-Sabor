package com.example.buensabor.entities.articulos;

import com.example.buensabor.entities.base.BaseEntity;
import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "historico_articulo")
public class HistoricoArticulo extends BaseEntity {

    @Column(name = "fecha", nullable = false)
    @NotNull
    private Date fecha;

    @Column(name = "precio_compra", nullable = false)
    @NotNull
    private double precioCompra;

    @Column(name = "cantidad", nullable = false)
    @NotNull
    private int cantidad;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;
}
