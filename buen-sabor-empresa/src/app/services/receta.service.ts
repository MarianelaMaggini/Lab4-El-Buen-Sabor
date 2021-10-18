import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Receta } from '../models/receta';

@Injectable({
  providedIn: 'root'
})
export class RecetaService {

  private recetaUrl: string = environment.recetaUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Obtener todas las recetas
  getAllRecetas(): Observable<Receta[]> {
    return this.http.get<Receta[]>(this.recetaUrl + "/todos", { headers: this.header });
  }

  // Obtener una receta por id
  getRecetaById(id: any): Observable<Receta> {
    return this.http.get<Receta>(this.recetaUrl + '/' + id, { headers: this.header });
  }

  // Obtener una receta por id de artículo elaborado detalle
  getRecetaByIdAed(id: any): Observable<Receta[]> {
    return this.http.get<Receta[]>(this.recetaUrl + '/detalle?id=' + id, { headers: this.header });
  }

  // Obtener una receta histórica por id de artículo elaborado detalle
  getRecetaHistoricaByIdAed(id: any): Observable<Receta[]> {
    return this.http.get<Receta[]>(this.recetaUrl + '/historica?id=' + id, { headers: this.header });
  }

  // Guardar-actualizar una receta
  saveReceta(receta: Receta): Observable<Receta> {
    return this.http.post<Receta>(this.recetaUrl, receta, { headers: this.header });
  }

}
