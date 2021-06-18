import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HistoricoArticulo } from '../models/historico-articulo';

@Injectable({
  providedIn: 'root'
})
export class HistoricoArticuloService {

  private historicoArticuloUrl: string = environment.historicoArticuloUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Obtener todo el histórico
  getAllHistoricoArticulos(): Observable<HistoricoArticulo[]> {
    return this.http.get<HistoricoArticulo[]>(this.historicoArticuloUrl + "/todos", { headers: this.header });
  }

  // Obtener un histórico artículo por id
  getHistoricoArticuloById(id: any): Observable<HistoricoArticulo> {
    return this.http.get<HistoricoArticulo>(this.historicoArticuloUrl + '/' + id, { headers: this.header });
  }

  // Obtener un histórico artículo por id de artículo
  getHistoricoArticuloByIdArticulo(id: any): Observable<HistoricoArticulo[]> {
    return this.http.get<HistoricoArticulo[]>(this.historicoArticuloUrl + '/articulo?id=' + id, { headers: this.header });
  }

  // Guardar-actualizar un histórico artículo
  saveHistoricoArticulo(historico: HistoricoArticulo): Observable<HistoricoArticulo> {
    return this.http.post<HistoricoArticulo>(this.historicoArticuloUrl, historico, { headers: this.header });
  }

  // Eliminar un histórico artículo por id
  deleteHistoricoArticuloById(id: any) {
    return this.http.delete(this.historicoArticuloUrl + '/' + id, { responseType: 'text' });
  }

}