import { UnidadMedida } from "./unidad-medida"
import { Rubro } from "./rubro";
import { TipoArticulo } from "./tipo-articulo";

export interface Articulo {

    id: number;
    denominacion: string;
    imagen: string;
    precioVenta: number;
    unidadMedida: UnidadMedida;
    rubro: Rubro;
    tipoArticulo: TipoArticulo;
}
