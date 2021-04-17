package com.example.buensabor.entity;

import java.util.List;

public class Cliente {

    private long id;
    private String nombre;
    private String apellido;
    private long telefono;
    private String email;
    private Usuario usuario;
    private Domicilio domicilio;
    private List<Pedido>pedidos;
}
