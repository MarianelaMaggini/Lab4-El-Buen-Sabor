import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Localidad } from '../models/localidad';

@Injectable({
  providedIn: 'root'
})
export class LocalidadService {
  private localidadUrl = environment.localidadUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) { }

  //petición para obtener todos los domicilios
  getLocalidades(): Observable<Localidad[]> {
    return this.http.get<Localidad[]>(this.localidadUrl + '/todos', { headers: this.header });
  }

  getLocalidadById(id:number):Observable<Localidad> {
    return this.http.get<Localidad>(this.localidadUrl + "/" + id, {headers: this.header})
  }

  saveLocalidad(localidad: Localidad): Observable<Localidad>{
    return this.http.post<Localidad>(this.localidadUrl, localidad, {headers: this.header});
  }
}
