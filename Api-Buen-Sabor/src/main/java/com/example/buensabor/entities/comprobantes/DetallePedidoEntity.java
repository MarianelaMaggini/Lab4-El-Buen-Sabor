package com.example.buensabor.entities.comprobantes;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.articulos.ArticuloEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class DetallePedidoEntity extends EntityBean {

    @Column(name = "cantidad", nullable = false)
    @NotNull
    private int cantidad;

    @Transient
    private double subtotal;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY,cascade ={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "id_articulo")
    private ArticuloEntity articuloEntity;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY,cascade ={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "id_pedido")
    private PedidoEntity pedidoEntity;
}
