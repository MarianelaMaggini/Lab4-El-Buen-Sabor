import { Pedido } from "./pedido";

export interface MercadoPagoDatos {
    
    identificadorPago: number;
    fechaCreacion: Date;
    fechaAprobacion: Date;
    formaPago: string;
    metodoPago: string;
    estado: string;
    pedido: Pedido;
}