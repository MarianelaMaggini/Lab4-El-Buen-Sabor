package com.example.buensabor.entities.comprobantes;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.articulos.Articulo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "detalle_pedido")
public class DetallePedido extends EntityBean {

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
