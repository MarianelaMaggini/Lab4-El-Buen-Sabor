import { PedidoEstado } from './pedido-estado';
import { TipoEnvio } from './tipo-envio';
import { Domicilio } from './domicilio';
import { Usuario } from './usuario';

export interface Pedido {

    numeroPedido: number;
    pedidoEstado: PedidoEstado;
    horaEstimadaFin: Date;
    tipoEnvio: TipoEnvio;
    domicilio: Domicilio;
    usuario: Usuario;
    formaPago: string;
}
