import { Rubro } from "./rubro";

export interface Articulo {
    id: number;
    denominacion: string;
    imagen: string;
    rubroEntity: Rubro;
}