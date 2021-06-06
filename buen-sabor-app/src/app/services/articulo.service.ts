  
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Articulo } from '../models/articulo';

@Injectable({
  providedIn: 'root'
})
export class ArticuloService {
  private articuloUrl = environment.articuloUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) { }

  //petición para obtener mediante un id rubro los productos asociados
  getArticulos(): Observable<Articulo[]> {
    return this.http.get<Articulo[]>(this.articuloUrl + '/todos', { headers: this.header });
  }

  getArticulosByTipoArticuloIdOrTipoArticulo(): Observable<Articulo[]>{
    return this.http.get<Articulo[]>(this.articuloUrl + '/idTiposArticulos?tipoUno=2&tipoDos=3', {headers: this.header});
  }

  getArticulosByTipoArticuloIdElaborado(): Observable<Articulo[]>{
    return this.http.get<Articulo[]>(this.articuloUrl + '/idTipoArticulo?id=2', {headers: this.header});
  }

  getArticulosByTipoArticuloIdNoElaborado(): Observable<Articulo[]>{
    return this.http.get<Articulo[]>(this.articuloUrl + '/idTipoArticulo?id=3', {headers: this.header});
  }

}