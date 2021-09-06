package com.example.buensabor.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tiempo {
    private int hora;
    private int minuto;
    private int diaNumero;
    private String diaNombre;
    private String zona;
}

