import { PedidoEstado } from './pedido-estado';
import { TipoEnvio } from './tipo-envio';
import { Domicilio } from './domicilio';
import { MercadoPagoDatos } from './mercado-pago-datos';
import { Usuario } from './usuario';

export interface Pedido {

    numeroPedido: number;
    horaEstimadaFin: Date;
    pedidoEstado: PedidoEstado;
    tipoEnvio: TipoEnvio;
    domicilio: Domicilio;
    mercadoPagoDatos: MercadoPagoDatos;
    usuario: Usuario;
    formaPago: string;
}