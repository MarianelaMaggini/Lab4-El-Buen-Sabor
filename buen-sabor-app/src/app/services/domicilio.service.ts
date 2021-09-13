import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Domicilio } from '../models/domicilio';

@Injectable({
  providedIn: 'root'
})
export class DomicilioService {
  private domicilioUrl = environment.domicilioUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) { }

  //Obtener todos los domicilios
  getDomicilios(): Observable<Domicilio[]> {
    return this.http.get<Domicilio[]>(this.domicilioUrl + '/todos', { headers: this.header });
  }

  //Obtener el domicilio por el id
  getDomicilioById(id:number):Observable<Domicilio> {
    return this.http.get<Domicilio>(this.domicilioUrl + "/" + id, {headers: this.header})
  }

  //Obtener los domicilios por el id del usuario
  getDomicilioByUserId(id:number):Observable<Domicilio[]>{
    return this.http.get<Domicilio[]>(this.domicilioUrl + '/usuario?id=' + id, {headers: this.header});
  }

  //Obtener los domicilios por el id de la localidad
  getDomicilioByLocalityId(id:number):Observable<Domicilio[]>{
    return this.http.get<Domicilio[]>(this.domicilioUrl + '/localidad?id=' + id, {headers: this.header});
  }
  
  //Guardar un domicilio
  saveDomicilio(domicilio: Domicilio): Observable<Domicilio>{
    return this.http.post<Domicilio>(this.domicilioUrl, domicilio, {headers: this.header});
  }
}
