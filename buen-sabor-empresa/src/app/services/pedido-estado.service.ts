import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PedidoEstado } from '../models/pedido-estado';

@Injectable({
  providedIn: 'root'
})
export class PedidoEstadoService {

  private pedidoEstadoUrl: string = environment.pedidoEstadoUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Obtener todos los estados de pedido
  getAllPedidoEstados(): Observable<PedidoEstado[]> {
    return this.http.get<PedidoEstado[]>(this.pedidoEstadoUrl + "/todos", { headers: this.header });
  }

  // Obtener un estado de pedido por id
  getPedidoEstadoById(id: any): Observable<PedidoEstado> {
    return this.http.get<PedidoEstado>(this.pedidoEstadoUrl + '/' + id, { headers: this.header });
  }
}
