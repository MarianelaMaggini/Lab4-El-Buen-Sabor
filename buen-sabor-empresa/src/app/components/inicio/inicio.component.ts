import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/models/usuario';
import { AuthService } from 'src/app/services/auth.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  usuario: Usuario;
  emailUsuario: string;
  constructor(
    private authService: AuthService,
    private tokenService: TokenService
    ) { }

  ngOnInit(): void {
    this.emailUsuario = this.tokenService.getUserName();
    this.getDatosUsuario();
  }
  getDatosUsuario(): void {
      this.authService.getDataUsuario(this.emailUsuario).subscribe(data => {
        this.usuario = data;
      })
  }
}
