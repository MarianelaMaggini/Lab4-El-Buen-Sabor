import { PedidoEstado } from './pedido-estado';
import { TipoEnvio } from './tipo-envio';
import { Domicilio } from './domicilio';
import { Factura } from './factura';
import { MercadoPagoDatos } from './mercado-pago-datos';
import { Usuario } from './usuario';

export interface Pedido {

    numeroPedido: number;
    pedidoEstado: PedidoEstado;
    horaEstimadaFin: Date;
    tipoEnvio: TipoEnvio;
    domicilio: Domicilio;
    // factura: Factura;
    mercadoPagoDatos: MercadoPagoDatos;
    usuario: Usuario;
    formaPago: string;
}
