import { Articulo } from "./articulo";
import { Factura } from "./factura";

export class DetalleFactura {
    id: number;
    cantidad: number;
    subtotal: number;
    articulo: Articulo;
    factura: Factura;
}
