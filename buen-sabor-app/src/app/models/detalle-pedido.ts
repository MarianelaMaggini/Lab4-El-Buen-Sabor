import { Articulo } from "./articulo";
import { Pedido } from "./pedido";

export interface DetallePedido {
    id: number;
    cantidad: number;
    subtotal: number;
    articulo: Articulo;
    pedido: Pedido;
}
