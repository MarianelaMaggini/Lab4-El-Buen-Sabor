import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Aed } from 'src/app/models/aed'
 
@Injectable({
  providedIn: 'root'
})
export class AedService {

  private aedUrl: string = environment.aedUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Obtener todos los detalles
  getAllAed(): Observable<Aed[]> {
    return this.http.get<Aed[]>(this.aedUrl + "/todos", { headers: this.header });
  }

  // Obtener un detalle por id
  getAedById(id: any): Observable<Aed> {
    return this.http.get<Aed>(this.aedUrl + '/' + id, { headers: this.header });
  }

  // Obtener un detalle por id de artículo
  getAedByIdArticulo(id: any): Observable<Aed> {
    return this.http.get<Aed>(this.aedUrl + '/articulo?id=' + id, { headers: this.header });
  }

  // Guardar-actualizar detalle
  saveAed(aed: Aed): Observable<Aed> {
    return this.http.post<Aed>(this.aedUrl, aed, { headers: this.header });
  }

  // Eliminar un detalle por id
  deleteAedById(id: any) {
    return this.http.delete(this.aedUrl + '/' + id, { responseType: 'text' });
  }
}
