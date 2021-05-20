package com.example.buensabor.entities.comprobantes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class FacturaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_factura")
    private long numeroFactura;

    @Column(name = "fecha", nullable = false)
    @NotNull
    private Date fecha;

    @Column(name = "monto_descuento", nullable = false)
    @NotNull
    private double montoDescuento;

    @Column(name = "forma_pago", length = 50, nullable = false)
    @NotNull
    private String formaPago;

    @Transient
    private double totalVenta;

    @Transient
    private double totalCosto;

    //@JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "facturaEntity")
    private List<DetalleFacturaEntity> detalleFacturaEntities = new ArrayList<>();
}
