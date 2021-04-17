package com.example.buensabor.entity;

import java.util.List;

public class RubroArticulo {

    private long id;
    private String denominacion;
    private RubroArticulo rubroArticuloPadre;
    private List<RubroArticulo>rubroArticulosHijos;
    private List<ArticuloInsumo> articuloInsumos;
}
