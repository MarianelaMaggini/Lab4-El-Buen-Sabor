import { Articulo } from "./articulo";
import { Pedido } from "./pedido";

export class ItemCart {
    id: number;
    articuloDenominacion: string;
    cantidad: number;
    precio: number;
    articulo: Articulo;
    constructor(articulo: Articulo){
        this.id = articulo.id;
        this.articuloDenominacion = articulo.denominacion;
        this.precio = articulo.precioVenta;
        this.cantidad = 1;
    }
}
