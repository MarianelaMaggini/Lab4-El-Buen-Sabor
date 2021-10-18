  
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

  /**
   * @param id 
   * @returns Observable de Articulo
   * @description Trae el artículo por su id
   */
  getArticuloById(id: number): Observable<Articulo> {
    return this.http.get<Articulo>(this.articuloUrl + '/' + id, { headers: this.header });
  }

  /**
   * @param id 
   * @returns Observable de Artículos
   * @description Trae los artículos seteados con el precio
   */
  getArticulosByTipoArticuloId(id:number): Observable<Articulo[]>{
    return this.http.get<Articulo[]>(this.articuloUrl + '/idTipoArticuloWithPrice?id=' + id, {headers: this.header});
  }

  /**
   * @param id 
   * @returns Observable de Artículos
   * @description Trae los artículos por el id rubro 
   */
  getArticulosByRubroId(id:number): Observable<Articulo[]>{
    return this.http.get<Articulo[]>(this.articuloUrl + '/idRubro?rubro=' + id, {headers: this.header});
  }

  /**
   * @param idUno 
   * @param idDos 
   * @returns Observable de Artículos
   * @description Trae los artículos por tipo articulo elaborado o no elaborado agrupados por el rubro
   */
  getArticuloByElaboradoOrNoElaboradoGroupByRubro(idUno:number, idDos:number): Observable<Articulo[]>{
    return this.http.get<Articulo[]>(this.articuloUrl + '/tipoArticulo?idUno=' + idUno + '&idDos=' + idDos, {headers: this.header});
  }

}