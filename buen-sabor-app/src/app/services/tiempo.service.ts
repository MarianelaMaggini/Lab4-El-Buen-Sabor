import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Tiempo } from '../models/tiempo';

@Injectable({
  providedIn: 'root'
})
export class TiempoService {
  private tiempoUrl = environment.tiempoUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) { }

  //petición para obtener todos los tipos envios
  getTiempo(): Observable<Tiempo> {
    return this.http.get<Tiempo>(this.tiempoUrl, { headers: this.header });
  }
}
