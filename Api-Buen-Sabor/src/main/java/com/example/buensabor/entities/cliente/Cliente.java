package com.example.buensabor.entities.cliente;

import com.example.buensabor.entities.comprobantes.Pedido;

import java.util.ArrayList;
import java.util.List;


public class Cliente {

    private String nombre;
    private String apellido;
    private long telefono;
    private String email;
    private Usuario usuario;
    private List<Domicilio> domicilios = new ArrayList<>();
    private List<Pedido>pedidos = new ArrayList<>();
}
