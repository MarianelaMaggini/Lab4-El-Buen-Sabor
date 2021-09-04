import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TipoEnvio } from '../models/tipo-envio';

@Injectable({
  providedIn: 'root'
})
export class TipoEnvioService {
  private tipoEnvioUrl = environment.tipoEnvioUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) { }

  //petición para obtener todos los tipos envios
  getTiposEnvios(): Observable<TipoEnvio[]> {
    return this.http.get<TipoEnvio[]>(this.tipoEnvioUrl + '/todos', { headers: this.header });
  }

  getTipoEnvioById(id:number):Observable<TipoEnvio> {
    return this.http.get<TipoEnvio>(this.tipoEnvioUrl + "/" + id, {headers: this.header})
  }
}
