package com.example.buensabor.entities.comprobantes;

import com.example.buensabor.entities.base.BaseEntity;
import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "factura")
public class Factura extends BaseEntity implements Serializable {

    @Column(name = "fecha", nullable = false)
    @NotNull
    private Date fecha;

    @Column(name = "monto_descuento", nullable = false)
    @NotNull
    private double montoDescuento;

    @OneToOne()
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

}
