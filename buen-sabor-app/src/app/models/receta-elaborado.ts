import { Articulo } from './articulo';
import { ArticuloElaboradoDetalle } from './articulo-elaborado-detalle';
import { UnidadMedida } from './unidad-medida';

export interface RecetaElaborado {
  id: number;
  cantidad: number;
  fechaBaja: Date;
  unidadMedida: UnidadMedida;
  articulo: Articulo;
  articuloElaboradoDetalle: ArticuloElaboradoDetalle;
}
