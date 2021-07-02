import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MercadoPagoService {

  private mpUrl = environment.mpUrl;
  constructor(private http: HttpClient) { }

  redirectMercadoPago(precio: number):Observable<string> {
    const requestOptions: Object = {
      /* other options here */
      responseType: 'text'
    }
    return this.http.get<string>(this.mpUrl + '?precio=' + precio, requestOptions);
  }
}
