import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Articulo } from '../models/articulo';

@Injectable({
  providedIn: 'root'
})
export class ArticuloService {

  private articuloUrl: string = environment.articuloUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Obtener todos los artículos
  getAllArticulos(): Observable<Articulo[]> {
    return this.http.get<Articulo[]>(this.articuloUrl + "/todos", { headers: this.header });
  }

  // Obtener todos los artículos activos
  getAllArticulosActivos(): Observable<Articulo[]> {
    return this.http.get<Articulo[]>(this.articuloUrl + "/todosActivos", { headers: this.header });
  }

  // Obtener un artículo por id
  getArticuloById(id: any): Observable<Articulo> {
    return this.http.get<Articulo>(this.articuloUrl + '/' + id, { headers: this.header });
  }

  // Obtener artículos por tipo (1, 2 ó 3)
  getArticulosByTipo(tipo: any): Observable<Articulo[]> {
    return this.http.get<Articulo[]>(this.articuloUrl + "/idTipoArticulo?id=" + tipo, { headers: this.header });
  }

  // Guardar-actualizar artículo
  saveArticulo(articulo: Articulo): Observable<Articulo> {
    return this.http.post<Articulo>(this.articuloUrl, articulo, { headers: this.header });
  }

}
