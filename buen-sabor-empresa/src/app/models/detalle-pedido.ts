import { Articulo } from "./articulo";
import { Pedido } from "./pedido";

export interface DetallePedido {

    id: number;
    cantidad: number;
    articulo: Articulo;
    pedido: Pedido;
}