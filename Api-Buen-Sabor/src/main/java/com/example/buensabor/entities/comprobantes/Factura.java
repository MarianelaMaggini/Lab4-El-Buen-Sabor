package com.example.buensabor.entities.comprobantes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private int numeroFactura;

    @Column(name = "fecha")
    @NotNull
    private Date fecha;

    @Column(name = "monto_descuento")
    @NotNull
    private double montoDescuento;

    @Column(name = "forma_pago", length = 50)
    @NotNull
    private String formaPago;

    @Transient
    private double totalVenta;

    @Transient
    private double totalCosto;

    @JsonIgnore
    @OneToMany(mappedBy = "factura", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<DetalleFactura> detalleFacturas = new ArrayList<>();
}
