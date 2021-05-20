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
    private final String TOKEN_MERCADO_PAGO = "TEST-8339743165209488-051723-d8d492eb1d5280305cca11c4032522da-164381654";
}
