import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RecetaElaborado } from '../models/receta-elaborado';

@Injectable({
  providedIn: 'root'
})
export class RecetaElaboradoService {

  private recetaElaboradoUrl = environment.recetaElaboradoUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

   //Obtener todas las recetas
   getRecetas(): Observable<RecetaElaborado[]> {
    return this.http.get<RecetaElaborado[]>(this.recetaElaboradoUrl + '/todos', { headers: this.header });
  }

  //Obtener la receta por el id
  getRecetaById(id:number):Observable<RecetaElaborado> {
    return this.http.get<RecetaElaborado>(this.recetaElaboradoUrl + "/" + id, {headers: this.header})
  }

  //Obtener todas las recetas por el id del articulo elaborado detalle
  getRecetaByArticuloDetalleId(id:number):Observable<RecetaElaborado[]>{
    return this.http.get<RecetaElaborado[]>(this.recetaElaboradoUrl + '/detalle?id=' + id, {headers: this.header});
  }  
}
 