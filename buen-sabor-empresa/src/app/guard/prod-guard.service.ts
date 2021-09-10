import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { TokenService } from '../services/token.service';

@Injectable({
  providedIn: 'root'
})
export class ProdGuardService implements CanActivate {

  realRol: string;
  constructor(private tokenService: TokenService, private router: Router) { }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    const expectedRol = route.data.expectedRol;
    //  this.realRol = this.tokenService.isAdmin() ? 'admin' : 'user';
    if (this.tokenService.isAdmin()) {
      this.realRol = 'admin'
    } else if (this.tokenService.isChef()) {
      this.realRol = 'cocinero'
    } else if (this.tokenService.isCashier()) {
      this.realRol = 'cajero'
    }
    else {
      this.realRol = 'user'
    }
    if (!this.tokenService.isLogged() || expectedRol.indexOf(this.realRol) < 0) {
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }
}
