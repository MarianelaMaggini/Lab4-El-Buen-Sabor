import { Component, OnInit } from '@angular/core';
import { SocialAuthService, SocialUser } from 'angularx-social-login';
import { Usuario } from 'src/app/models/usuario';
import { Articulo } from 'src/app/models/articulo';
import { AuthService } from 'src/app/services/auth.service';
import { MessageService } from 'src/app/services/message.service';
import { StorageService } from 'src/app/services/storage.service';
import { TokenService } from 'src/app/services/token.service';

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
    private messageService: MessageService
    ) {
      this.cantidad = 0;
    }

  ngOnInit(): void {
    this.getUser();
    this.countCart();
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

  countCart():void {
    this.messageService.getMessage().subscribe(() => {
      this.cantidad += 1;
    });
  }
}
