import { Articulo } from './articulo';

export interface ArticuloElaboradoDetalle {
  id: number;
  descripcion: string;
  tiempoEstimadoCocina: number;
  articulo: Articulo;
}
