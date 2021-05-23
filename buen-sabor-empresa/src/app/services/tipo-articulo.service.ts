import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TipoArticulo } from '../models/tipo-articulo';

@Injectable({
  providedIn: 'root'
})
export class TipoArticuloService {
  private tipoArticuloUrl = environment.tipoArticuloUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) { }

  //obtener todos los tipo articulos
  getAll(): Observable<TipoArticulo[]> {
    return this.http.get<TipoArticulo[]>(this.tipoArticuloUrl+"/todos", { headers: this.header });
  }
}
