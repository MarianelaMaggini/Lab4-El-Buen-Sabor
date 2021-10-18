package com.example.buensabor.entities.comprobantes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "factura")
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_factura")
    private Long numeroFactura;

    @Column(name = "fecha", nullable = false)
    @NotNull
    private Date fecha;

    @Column(name = "monto_descuento", nullable = false)
    @NotNull
    private double montoDescuento;

    @OneToOne()
    @JoinColumn(name = "numero_pedido")
    private Pedido pedido;

}
