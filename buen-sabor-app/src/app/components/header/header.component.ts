import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/models/usuario';
import { AuthService } from 'src/app/services/auth.service';
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
  cantidad: number;
  usuario: Usuario;
  cartItems: any = [];
  constructor(
    private storageService: StorageService,
    private tokenService: TokenService,
    private authService: AuthService
    ) {}

  ngOnInit(): void {
    this.getDatosUsuario();
    let cant = 0;
    if (this.storageService.existCart()) {
      this.storageService.getCart().forEach((item) => {
        cant += item.cantidad;
      })
    this.cantidad = cant;
    }else {
      this.cantidad = 0;
    }
    this.isAdmin = this.tokenService.isAdmin();
    this.isLogged = this.tokenService.isLogged();
    
  }

  onLogOut(): void {
    this.tokenService.logOut();
    window.location.reload();
  }

  getDatosUsuario(): void {
    let userName = this.tokenService.getUserName()
      this.authService.getDataUsuario(userName).subscribe(data => {
        console.log(data)
        this.usuario = data;
      })
  }
}
