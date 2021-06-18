import { Articulo } from "./articulo";

export interface HistoricoArticulo {

    id: number;
    fecha: Date;
    cantidad: number;
    precioCompra: number;
    unidadMedida: string;
    articulo: Articulo;
}