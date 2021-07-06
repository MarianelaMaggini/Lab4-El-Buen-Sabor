import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
const TOKEN_KEY = 'AuthToken';
@Injectable({
  providedIn: 'root',
})
export class TokenService {
  roles: Array<string> = [];
  constructor(private router: Router) {}

  public setToken(token: string): void {
    sessionStorage.removeItem(TOKEN_KEY);
    sessionStorage.setItem(TOKEN_KEY, token);
  }
  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY)!;
  }

  public isLogged(): boolean {
    if (this.getToken()) {
      return true;
    }else{
      return false;
    }
  }

  public getUserName(): string {
    if (!this.isLogged()) {
      return null!;
    }
    const token = this.getToken();
    const payload = token.split('.')[1];
    const payloadDecoded = atob(payload);
    const values = JSON.parse(payloadDecoded);
    const userName = values.sub;
    return userName;
  }

  public isAdmin(): boolean {
    if (!this.isLogged()) {
      return false;
    }
    const token = this.getToken();
    const payload = token.split('.')[1];
    const payloadDecoded = atob(payload);
    const values = JSON.parse(payloadDecoded);
    const roles = values.roles;
    if (roles.indexOf('ROLE_ADMIN') < 0) {
      return false;
    }else{
      return true;
    }
  }
  public logOut(): void {
    sessionStorage.clear();
    this.router.navigate(['/login'])
  }
}
