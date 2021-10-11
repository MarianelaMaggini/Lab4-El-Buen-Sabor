import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Factura } from '../models/factura';

@Injectable({
  providedIn: 'root'
})
export class FacturaService {

  private facturaUrl: string = environment.facturaUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Obtener todas las facturas
  getAllFacturas(): Observable<Factura[]> {
    return this.http.get<Factura[]>(this.facturaUrl + "/todos", { headers: this.header });
  }

  // Obtener una factura por id
  getFacturaById(id: any): Observable<Factura> {
    return this.http.get<Factura>(this.facturaUrl + '/' + id, { headers: this.header });
  }

}
