import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { DetallePedido } from '../models/detalle-pedido';

@Injectable({
  providedIn: 'root'
})
export class DetallePedidoService {

  private detallePedidoUrl: string = environment.detallePedidoUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Obtener todos los detalles de pedido
  getAllDetallesPedido(): Observable<DetallePedido[]> {
    return this.http.get<DetallePedido[]>(this.detallePedidoUrl + "/todos", { headers: this.header });
  }

  // Obtener un detalle de pedido por id de pedido
  getDetalleByIdPedido(id: any): Observable<DetallePedido[]> {
    return this.http.get<DetallePedido[]>(this.detallePedidoUrl + '/pedido?id=' + id, { headers: this.header });
  }

}
