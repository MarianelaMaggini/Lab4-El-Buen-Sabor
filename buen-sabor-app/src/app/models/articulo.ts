import { Rubro } from "./rubro";
import { TipoArticulo } from "./tipo-articulo";
import { UnidadMedida } from "./unidad-medida";

export interface Articulo {
    id: number;
    denominacion: string;
    imagen: string;
    precioVenta: number;
    fechaBaja: Date;
    tipoArticulo: TipoArticulo;
    rubro: Rubro;
    unidadMedida: UnidadMedida;
}