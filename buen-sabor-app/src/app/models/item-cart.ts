import { Articulo } from "./articulo";
import { Pedido } from "./pedido";
import { TipoArticulo } from "./tipo-articulo";

export class ItemCart {
    id: number;
    articuloDenominacion: string;
    cantidad: number;
    precio: number;
    articulo: Articulo;
    tipoArticulo: TipoArticulo;
    constructor(articulo: Articulo){
        this.id = articulo.id;
        this.articuloDenominacion = articulo.denominacion;
        this.precio = articulo.precioVenta;
        this.cantidad = 1;
        this.tipoArticulo = articulo.tipoArticulo;
    }
}
