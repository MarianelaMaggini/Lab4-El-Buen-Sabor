import { Rubro } from "./rubro";

export interface Articulo {
    id: number;
    denominacion: string;
    imagen: string;
    precioVenta: number;
    rubro: Rubro;
}