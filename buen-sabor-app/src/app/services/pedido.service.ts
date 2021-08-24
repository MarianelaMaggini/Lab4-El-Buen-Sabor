import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Pedido } from '../models/pedido';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {
  private pedidoUrl = environment.pedidoUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) { }

  //petición para obtener todos los pedidos
  getTiposEnvios(): Observable<Pedido[]> {
    return this.http.get<Pedido[]>(this.pedidoUrl + '/todos', { headers: this.header });
  }

  savePedido(pedido: Pedido): Observable<Pedido>{
    return this.http.post<Pedido>(this.pedidoUrl, pedido, {headers: this.header});
  }
}
