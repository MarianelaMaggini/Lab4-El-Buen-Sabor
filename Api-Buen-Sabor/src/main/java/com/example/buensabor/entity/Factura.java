package com.example.buensabor.entity;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date fecha;
    private int numero;
    private double montoDescuento;
    private String formaPago;
    private String nroTarjeta;
    private double totalVenta;
    private double totalCosto;
    private List<DetalleFactura> detalleFacturas = new ArrayList<>();
}
