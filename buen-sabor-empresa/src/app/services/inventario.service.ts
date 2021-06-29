import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { Inventario } from 'src/app/models/inventario';

@Injectable({
  providedIn: 'root'
})
export class InventarioService {

  private inventarioUrl: string = environment.inventarioUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Obtener todo el inventario
  getAllInventario(): Observable<Inventario[]> {
    return this.http.get<Inventario[]>(this.inventarioUrl + "/todos", { headers: this.header });
  }

  // Guardar-actualizar inventario
  saveUpdateInventario(inventario: Inventario): Observable<Inventario> {
    return this.http.post<Inventario>(this.inventarioUrl, inventario, { headers: this.header });
  }

}
