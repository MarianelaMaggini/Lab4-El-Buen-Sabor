import { Articulo } from "./articulo";

export interface Aed {

    id: number;
    descripcion: string;
    tiempoEstimadoCocina: number;
    articulo: Articulo;
}