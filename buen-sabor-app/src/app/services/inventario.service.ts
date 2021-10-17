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
  getInventarios(): Observable<Inventario[]> {
    return this.http.get<Inventario[]>(this.inventarioUrl + '/todos', { headers: this.header });
  }

  //Obtener todos los inventarios por el id del articulo
  getInventarioByArticuloId(id:number):Observable<Inventario[]>{
    return this.http.get<Inventario[]>(this.inventarioUrl + '/articulo?id=' + id, {headers: this.header});
  } 

  //Actualizar un inventario
  updateInventario(inventario: Inventario): Observable<Inventario>{
    return this.http.post<Inventario>(this.inventarioUrl, inventario, {headers: this.header});
  }
}
