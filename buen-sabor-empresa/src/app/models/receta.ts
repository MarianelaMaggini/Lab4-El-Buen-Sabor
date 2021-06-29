import { Aed } from "./aed";
import { Articulo } from "./articulo";
import { UnidadMedida } from "./unidad-medida";

export interface Receta {
    
    id: number;
    cantidad: number;
    articulo: Articulo;
    articuloElaboradoDetalle: Aed;
    unidadMedida: UnidadMedida;
}