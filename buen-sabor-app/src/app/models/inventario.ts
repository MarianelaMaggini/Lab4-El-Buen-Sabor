import { Articulo } from "./articulo";

export interface Inventario {
    id:number;
    stockActual: number;
    stockMinimo: number;
    articulo: Articulo;
}
