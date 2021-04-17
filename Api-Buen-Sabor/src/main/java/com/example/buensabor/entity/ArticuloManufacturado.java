package com.example.buensabor.entity;

import java.util.ArrayList;
import java.util.List;

public class ArticuloManufacturado {
    private long id;
    private int tiempoEstimadoCocina;
    private String denominacion;
    private double precioVenta;
    private String imagen;
    private List<DetalleFactura> detalleFacturas;
    private List<DetallePedido> detallePedidos;
    private List<ArticuloManufacturadoDetalle> articuloManufacturadoDetalles = new ArrayList<>();
}
