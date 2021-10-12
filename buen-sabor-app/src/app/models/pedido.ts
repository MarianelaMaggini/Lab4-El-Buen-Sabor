import { Domicilio } from "./domicilio";
import { PedidoEstado } from "./pedido-estado";
import { TipoEnvio } from "./tipo-envio";
import { Usuario } from "./usuario";

export interface Pedido {
    numeroPedido: number;
    horaEstimadaFin: Date;
    total: number;
    usuario: Usuario;
    tipoEnvio: TipoEnvio;
    pedidoEstado: PedidoEstado;
    domicilio: Domicilio;
    formaPago: string;
}
