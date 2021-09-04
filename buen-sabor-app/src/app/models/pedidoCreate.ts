import { Domicilio } from "./domicilio";
import { PedidoEstado } from "./pedido-estado";
import { TipoEnvio } from "./tipo-envio";
import { Usuario } from "./usuario";

export class PedidoCreate {
    numeroPedido: number;
    horaEstimadaFin: Date;
    total: number;
    usuario: Usuario;
    tipoEnvio: TipoEnvio;
    pedidoEstado: PedidoEstado;
    domicilio: Domicilio;

    constructor(numeroPedido: number, horaEstimadaFin: Date, total: number, usuario: Usuario, tipoEnvio: TipoEnvio, pedidoEstado: PedidoEstado, domicilio: Domicilio) {
        this.numeroPedido = numeroPedido;
        this.horaEstimadaFin = horaEstimadaFin;
        this.total = total;
        this.usuario = usuario;
        this.tipoEnvio = tipoEnvio;
        this.pedidoEstado = pedidoEstado;
        this.domicilio = domicilio;
    }
}