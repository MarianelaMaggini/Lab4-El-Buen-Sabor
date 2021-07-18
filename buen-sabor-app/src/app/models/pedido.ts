import { Domicilio } from "./domicilio";
import { Factura } from "./factura";
import { PedidoEstado } from "./pedido-estado";
import { TipoEnvio } from "./tipo-envio";
import { Usuario } from "./usuario";

export class Pedido {
    numeroPedido: number;
    horaEstimadaFin: Date;
    total: number;
    usuario: Usuario;
    tipoEnvio: TipoEnvio;
    pedidoEstado: PedidoEstado;
    domicilio: Domicilio;
    factura: Factura;
}
