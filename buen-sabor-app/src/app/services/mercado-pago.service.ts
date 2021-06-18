import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MercadoPagoService {

  private mpUrl = environment.mpUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) { }

  getMp(precio: number):Observable<string> {
    return this.http.get<string>(this.mpUrl + '?precio=' + precio, {headers: this.header});
  }
}
