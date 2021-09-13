import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { DetallePedido } from '../models/detalle-pedido';

@Injectable({
  providedIn: 'root'
})
export class DetallePedidoService {
  private detallePedidoUrl = environment.detallesPedidoUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) { }

  //Guardar el detalle del pedido
  saveDetallePedido(detallePedido: DetallePedido): Observable<DetallePedido>{
    return this.http.post<DetallePedido>(this.detallePedidoUrl, detallePedido, {headers: this.header});
  }
  //Obtener los detalles del pedido por el id del pedido
  getDetallesPedidosByPedido(id:number): Observable<DetallePedido[]> {
    return this.http.get<DetallePedido[]>(this.detallePedidoUrl + '/pedido?id=' + id, { headers: this.header });
  }
}
