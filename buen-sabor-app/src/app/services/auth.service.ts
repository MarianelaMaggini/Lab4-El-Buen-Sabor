import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { JwtDto } from '../models/jwt-dto';
import { LoginUsuario } from '../models/login-usuario';
import { NuevoUsuario } from '../models/nuevo-usuario';
import { Usuario } from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authUrl = environment.authUrl;
  constructor(private http: HttpClient) { }

  public nuevo(nuevoUsuario: NuevoUsuario):Observable<any>{
    const requestOptions: Object = {
      /* other options here */
      responseType: 'text'
    }
    return this.http.post<any>(this.authUrl + 'nuevo', nuevoUsuario, requestOptions);
  }

  public login(loginUsuario: LoginUsuario):Observable<JwtDto>{
    return this.http.post<JwtDto>(this.authUrl + 'login', loginUsuario);
  }

  public refresh(jwtDto: JwtDto):Observable<JwtDto>{
    return this.http.post<JwtDto>(this.authUrl + 'refresh', jwtDto);
  }

  getDataUsuario(email: string): Observable<Usuario> {
    return this.http.get<Usuario>(this.authUrl + email);
  }
}
