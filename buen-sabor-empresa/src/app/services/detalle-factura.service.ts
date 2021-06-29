import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { DetalleFactura } from '../models/detalle-factura';

@Injectable({
  providedIn: 'root'
})
export class DetalleFacturaService {

  private detalleFacturaUrl: string = environment.detalleFacturaUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Obtener todos los detalles de factura
  getAllDetallesFactura(): Observable<DetalleFactura[]> {
    return this.http.get<DetalleFactura[]>(this.detalleFacturaUrl + "/todos", { headers: this.header });
  }

  // Obtener un detalle de factura por id de factura
  getDetalleByIdFactura(id: any): Observable<DetalleFactura[]> {
    return this.http.get<DetalleFactura[]>(this.detalleFacturaUrl + '/factura?id=' + id, { headers: this.header });
  }

}
