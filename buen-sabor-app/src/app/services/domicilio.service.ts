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

  //petición para obtener todos los domicilios
  getDomicilios(): Observable<Domicilio[]> {
    return this.http.get<Domicilio[]>(this.domicilioUrl + '/todos', { headers: this.header });
  }

  getDomicilioById(id:number):Observable<Domicilio> {
    return this.http.get<Domicilio>(this.domicilioUrl + "/" + id, {headers: this.header})
  }

  getDomicilioByUserId(id:number):Observable<Domicilio[]>{
    return this.http.get<Domicilio[]>(this.domicilioUrl + '/id?usuario=' + id, {headers: this.header});
  }
}
