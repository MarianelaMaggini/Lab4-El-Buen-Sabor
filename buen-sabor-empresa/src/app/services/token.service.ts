import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
const TOKEN_KEY = 'AuthToken';
@Injectable({
  providedIn: 'root'
})
export class TokenService {

  roles: Array<string> = [];
  constructor(private router: Router) {}

  public setToken(token: string): void {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, token);
  }
  public getToken(): string {
    return localStorage.getItem(TOKEN_KEY)!;
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
    const roles = this.getRoles();
    if (roles.indexOf('ROLE_ADMIN') < 0) {
      return false;
    }else{
      return true;
    }
  }

  public isChef(): boolean{
    if (!this.isLogged()) {
      return false;
    }
    const roles = this.getRoles();
    if (roles.indexOf('ROLE_COCINERO') < 0) {
      return false;
    }else{
      return true;
    }
  }

  public isCashier(): boolean{
    if (!this.isLogged()) {
      return false;
    }
    const roles = this.getRoles();
    if (roles.indexOf('ROLE_CAJERO') < 0) {
      return false;
    }else{
      return true;
    }
  }

  public getRoles():any{
    const token = this.getToken();
    const payload = token.split('.')[1];
    const payloadDecoded = atob(payload);
    const values = JSON.parse(payloadDecoded);
    const roles = values.roles;
    return roles;
  }

  public logOut(): void {
    window.localStorage.clear();
    
  }
}
