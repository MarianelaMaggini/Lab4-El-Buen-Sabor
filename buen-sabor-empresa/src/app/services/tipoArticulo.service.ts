import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TipoArticulo } from 'src/app/models/tipo-articulo';

@Injectable({
  providedIn: 'root'
})
export class TipoArticuloService {

  private tipoArticuloUrl = environment.tipoArticuloUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Obtener todos los tipos de artículo
  getAllTiposArticulo(): Observable<TipoArticulo[]> {
    return this.http.get<TipoArticulo[]>(this.tipoArticuloUrl + "/todos", { headers: this.header });
  }

  // Obtener un tipo de artículo por id
  getTipoArticuloById(id: any): Observable<TipoArticulo> {
    return this.http.get<TipoArticulo>(this.tipoArticuloUrl + '/' + id, { headers: this.header });
  }

}
