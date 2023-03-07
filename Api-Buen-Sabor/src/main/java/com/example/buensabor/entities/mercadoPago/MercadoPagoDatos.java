package com.example.buensabor.entities.mercadoPago;

import com.example.buensabor.entities.comprobantes.Pedido;
import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "mercado_pago_datos")
public class MercadoPagoDatos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identificador_pago")
    private Long identificadorPago;

    @Column(name = "fecha_creacion", nullable = false)
    @NotNull
    private Date fechaCreacion;

    @Column(name = "fecha_aprobacion", nullable = false)
    @NotNull
    private Date fechaAprobacion;

    @Column(name = "forma_pago", length = 65, nullable = false)
    @NotNull
    private String formaPago;

    @Column(name = "metodo_pago", length = 65, nullable = false)
    @NotNull
    private String metodoPago;

    @Transient
    private String nroTarjeta;

    @Column(name = "estado", length = 65, nullable = false)
    @NotNull
    private String estado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;
}
