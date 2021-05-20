package com.example.buensabor.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuracion {
    private int cantidadCocineros;
    private String emailEmpresa;
    private final String TOKEN_MERCADO_PAGO = "";
}
