import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Rubro } from '../models/rubro';

@Injectable({
  providedIn: 'root'
})
export class RubroService {
  private rubroUrl = environment.rubroUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
  constructor(private http: HttpClient) { }

  getRubroByIdArticuloInsumo(id:number):Observable<Rubro[]>{
    return this.http.get<Rubro[]>(this.rubroUrl + '/rubro?id=' + id, { headers:this.header })
  }
}
