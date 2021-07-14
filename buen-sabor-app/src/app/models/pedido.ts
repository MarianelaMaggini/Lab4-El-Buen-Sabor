import { Domicilio } from "./domicilio";
import { TipoEnvio } from "./tipo-envio";
import { Usuario } from "./usuario";

export class Pedido {
    numeroPedido: number;
    horaEstimadaFin: Date;
    total: number;
    usuario: Usuario;
    tipoEnvio: TipoEnvio;
    domicilio: Domicilio;
}
