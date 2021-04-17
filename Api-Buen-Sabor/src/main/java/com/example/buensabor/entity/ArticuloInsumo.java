package com.example.buensabor.entity;

import java.util.List;

public class ArticuloInsumo {

    private long id;
    private String denominacion;
    private double precioCompra;
    private double precioVenta;
    private double stockActual;
    private double stockMinimo;
    private String unidadMedida;
    private boolean esInsumo;
    private List<ArticuloManufacturadoDetalle> articuloManufacturadoDetalles;
    private List<DetallePedido> detallePedidos;
    private List<DetalleFactura> detalleFacturas;

}
