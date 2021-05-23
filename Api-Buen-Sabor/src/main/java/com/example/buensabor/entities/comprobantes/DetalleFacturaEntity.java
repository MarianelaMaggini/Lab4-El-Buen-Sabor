package com.example.buensabor.entities.comprobantes;

import com.example.buensabor.entities.EntityBean;
import com.example.buensabor.entities.articulos.ArticuloEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "detalle_factura")
public class DetalleFacturaEntity extends EntityBean {

    @Column(name = "cantidad", nullable = false)
    @NotNull
    private int cantidad;

    @Transient
    private double subtotal;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_articulo")
    private ArticuloEntity articuloEntity;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_factura")
    private FacturaEntity facturaEntity;
}
