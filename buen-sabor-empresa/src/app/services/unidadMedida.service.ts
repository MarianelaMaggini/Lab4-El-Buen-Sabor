import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UnidadMedida } from '../models/unidad-medida'; 

@Injectable({
  providedIn: 'root'
})
export class UnidadMedidaService {

  private unidadMedidaUrl: string = environment.unidadMedidaUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Obtener todas las unidades de medida
  getAllUnidades(): Observable<UnidadMedida[]> {
    return this.http.get<UnidadMedida[]>(this.unidadMedidaUrl + "/todos", { headers: this.header });
  }

  // Obtener una unidad de medida por id
  getUnidadById(id: any): Observable<UnidadMedida> {
    return this.http.get<UnidadMedida>(this.unidadMedidaUrl + '/' + id, { headers: this.header });
  }

}
