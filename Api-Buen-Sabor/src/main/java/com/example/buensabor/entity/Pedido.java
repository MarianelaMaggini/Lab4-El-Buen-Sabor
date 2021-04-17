package com.example.buensabor.entity;

import java.util.Date;

public class Pedido {

    private long id;
    private int numero;
    private Date fecha;
    private int estado;
    private Date horaEstimadaFin;
    private int tipoEnvio;
    private double total;
    private Domicilio domicilio;
    private Factura factura;
    private MercadoPagoDatos mercadoPagoDatos;

}
