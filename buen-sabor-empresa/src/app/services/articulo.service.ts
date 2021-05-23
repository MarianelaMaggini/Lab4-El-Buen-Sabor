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
  getArticuloPorIdRubro(id: number): Observable<Articulo[]> {
    return this.http.get<Articulo[]>(this.articuloUrl + '/idRubro?rubro=' + id, { headers: this.header });
  }

}
