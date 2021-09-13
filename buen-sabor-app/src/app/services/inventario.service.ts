import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Inventario } from '../models/inventario';

@Injectable({
  providedIn: 'root'
})
export class InventarioService {
  private inventarioUrl = environment.inventarioUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) { }

  //Obtener todos los inventarios
  getArticulos(): Observable<Inventario[]> {
    return this.http.get<Inventario[]>(this.inventarioUrl + '/todos', { headers: this.header });
  }

  //Actualizar un inventario
  updateInventario(pedido: Inventario): Observable<Inventario>{
    return this.http.post<Inventario>(this.inventarioUrl, pedido, {headers: this.header});
  }
}
