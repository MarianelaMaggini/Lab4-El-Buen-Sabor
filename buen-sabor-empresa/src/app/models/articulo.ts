import { Rubro } from "./rubro";
import { TipoArticulo } from "./tipo-articulo";

export interface Articulo {
    id: number;
    denominacion: string;
    imagen: string;
    tipoArticuloEntity: TipoArticulo;
    rubroEntity: Rubro;
}
