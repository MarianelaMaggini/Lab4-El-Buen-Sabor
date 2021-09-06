import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ArticuloElaboradoDetalle } from '../models/articulo-elaborado-detalle';

@Injectable({
  providedIn: 'root'
})
export class ArticuloElaboradoDetalleService {

  private articuloElaboradoDetallesUrl = environment.articuloElaboradoDetallesUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

     /* petición para obtener todos los detalles de los artículos elaborados */
  getArtElaboradoDetalles(): Observable<ArticuloElaboradoDetalle[]> {
      return this.http.get<ArticuloElaboradoDetalle[]>(this.articuloElaboradoDetallesUrl + '/todos', { headers: this.header });
  }
  
  getArtElaboradoDetalleById(id:number):Observable<ArticuloElaboradoDetalle> {
      return this.http.get<ArticuloElaboradoDetalle>(this.articuloElaboradoDetallesUrl + "/" + id, {headers: this.header})
  }
  
  getArtElaboradoDetalleByArticuloId(id:number):Observable<ArticuloElaboradoDetalle[]>{
      return this.http.get<ArticuloElaboradoDetalle[]>(this.articuloElaboradoDetallesUrl + '/articulo?id=' + id, {headers: this.header});
  } 
}
