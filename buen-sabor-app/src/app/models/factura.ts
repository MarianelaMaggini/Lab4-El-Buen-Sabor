import { Pedido } from "./pedido";

export interface Factura {
    numeroFactura: number;
    fecha: Date;
    montoDescuento: number;
    pedido: Pedido;
}
