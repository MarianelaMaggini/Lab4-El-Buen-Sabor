package com.example.buensabor.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "mercado_pago_datos")
public class MercadoPagoDatos extends EntityBean{

    private long identificadorPago;
    private Date fechaCreacion;
    private Date fechaAprobacion;
    private String formaPago;
    private String metodoPago;
    private String nroTarjeta;
    private String estado;
}
