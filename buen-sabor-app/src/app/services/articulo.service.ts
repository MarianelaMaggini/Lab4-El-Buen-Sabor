  
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

  //petición para obtener todos los articulos
  getArticulos(): Observable<Articulo[]> {
    return this.http.get<Articulo[]>(this.articuloUrl + '/todos', { headers: this.header });
  }

  //petición para obtener todos los articulos
  getArticuloById(id: number): Observable<Articulo> {
    return this.http.get<Articulo>(this.articuloUrl + '/' + id, { headers: this.header });
  }

  getArticulosByTipoArticuloIdOrTipoArticulo(): Observable<Articulo[]>{
    return this.http.get<Articulo[]>(this.articuloUrl + '/idTiposArticulos?tipoUno=2&tipoDos=3', {headers: this.header});
  }

  getArticulosByTipoArticuloId(id:number): Observable<Articulo[]>{
    return this.http.get<Articulo[]>(this.articuloUrl + '/idTipoArticulo?id=' + id, {headers: this.header});
  }

  getArticulosByRubroId(id:number): Observable<Articulo[]>{
    return this.http.get<Articulo[]>(this.articuloUrl + '/idRubro?rubro=' + id, {headers: this.header});
  }

  getArticuloByElaboradoOrNoElaboradoGroupByRubro(idUno:number, idDos:number): Observable<Articulo[]>{
    return this.http.get<Articulo[]>(this.articuloUrl + '/tipoArticulo?idUno=' + idUno + '&idDos=' + idDos, {headers: this.header});
  }

}