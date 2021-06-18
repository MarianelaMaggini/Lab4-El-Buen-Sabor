import { Articulo } from "./articulo";
import { Pedido } from "./pedido";

export class DetallePedido {
    id: number;
    articuloDenominacion: string;
    cantidad: number;
    precio: number;
    articulo: Articulo;
    pedido: Pedido;
    constructor(articulo: Articulo){
        this.id = articulo.id;
        this.articuloDenominacion = articulo.denominacion;
        this.precio = articulo.precioVenta;
        this.cantidad = 1;
    }
}
