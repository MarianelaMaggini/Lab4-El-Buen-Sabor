import { Component, OnInit } from '@angular/core';
import { SocialAuthService, SocialUser } from 'angularx-social-login';
import { Usuario } from 'src/app/models/usuario';
import { AuthService } from 'src/app/services/auth.service';
import { StorageService } from 'src/app/services/storage.service';
import { TokenService } from 'src/app/services/token.service';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  isLogged = false;
  isAdmin = false;
  userLogged: SocialUser;
  cantidad: number;
  usuario: Usuario;
  cartItems: any = [];
  constructor(
    private storageService: StorageService,
    private tokenService: TokenService,
    private authService: AuthService,
    private socialAuthService: SocialAuthService,
    private dataService: DataService
    ) {
      this.cantidad = 0;
    }

  ngOnInit(): void {
    this.getUser();
    if (this.storageService.exist('quantity')) {
      this.cantidad = this.storageService.get('quantity');
    }
    this.addCountCart();
    this.removeCountCart();
    this.isAdmin = this.tokenService.isAdmin();
    this.isLogged = this.tokenService.isLogged();
    this.socialAuthService.authState.subscribe((data) => {
      this.userLogged = data;
      this.isLogged = (this.userLogged != null && this.tokenService.getToken() != null);
    })
  }

  onLogOut(): void {
    this.isLogged = false;
    this.storageService.clear('cart');
    this.tokenService.logOut();
    window.location.reload()
  }

  logOutWithGoogle(){
    this.socialAuthService.signOut().then(data => {
        this.tokenService.logOut();
        this.isLogged = false;
        this.storageService.clear('cart');
    });
  }

  getUser(): void {
    let userName = this.tokenService.getUserName()
      this.authService.getDataUsuario(userName).subscribe(data => {
        this.usuario = data;
    })
  }

  addCountCart():void {
    this.dataService.quantityAdd$.subscribe(cantidadCart => {
        this.cantidad += cantidadCart;
        this.storageService.set('quantity', this.cantidad);
      })
  }

  removeCountCart(): void{
    this.dataService.quantityRemove$.subscribe(cantidadCart => {
      this.cantidad -= cantidadCart;
      this.storageService.set('quantity', this.cantidad);
    });  
  }
}
