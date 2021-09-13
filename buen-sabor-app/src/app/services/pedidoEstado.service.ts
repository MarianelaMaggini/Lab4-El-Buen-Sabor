import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PedidoEstado } from '../models/pedido-estado';

@Injectable({
  providedIn: 'root'
})
export class PedidoEstadoService {
  private pedidoEstadoUrl = environment.pedidoEstadosUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) { }

  //Obtener un pedido estado por el id
  getPedidoEstadoById(id:number):Observable<PedidoEstado> {
    return this.http.get<PedidoEstado>(this.pedidoEstadoUrl + "/" + id, {headers: this.header})
  }
}
