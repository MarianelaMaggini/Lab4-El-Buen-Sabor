package com.example.buensabor.enums;

public enum formaPago {

    EFECTIVO(0, "Efectivo"),
    MERCADOPAGO(1, "Mercado Pago");

    private final Integer code;
    private final String nombreForma;

    formaPago(Integer code, String nombreForma) {
        this.code = code;
        this.nombreForma = nombreForma;
    }

    public Integer getCode() {
        return code;
    }

    public String getNombreForma() {
        return nombreForma;
    }

}
