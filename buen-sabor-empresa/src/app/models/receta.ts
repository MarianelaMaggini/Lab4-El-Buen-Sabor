import { Aed } from "./aed";
import { Articulo } from "./articulo";
import { UnidadMedida } from "./unidadMedida";

export interface Receta {
    
    id: number;
    cantidad: number;
    articulo: Articulo;
    articuloElaboradoDetalle: Aed;
    unidadMedida: UnidadMedida;
}