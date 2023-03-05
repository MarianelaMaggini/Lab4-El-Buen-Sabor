package com.example.buensabor.entities.comprobantes;

import com.example.buensabor.entities.base.BaseEntity;
import com.example.buensabor.entities.articulos.Articulo;
import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "detalle_pedido")
public class DetallePedido extends BaseEntity {

    @Column(name = "cantidad", nullable = false)
    @NotNull
    private int cantidad;

    @Column(name = "subtotal", nullable = false)
    @NotNull
    private double subtotal;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;
}
