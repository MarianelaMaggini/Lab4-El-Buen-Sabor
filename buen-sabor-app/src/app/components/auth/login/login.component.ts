import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LoginUsuario } from 'src/app/models/login-usuario';
import { AuthService } from 'src/app/services/auth.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginUsuario: LoginUsuario;
  email: string;
  clave: string;
  mensajeError: string;
  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
  }
  onLogin(): void {
    this.loginUsuario = new LoginUsuario(this.email, this.clave);
    this.authService.login(this.loginUsuario).subscribe(data => {
      this.tokenService.setToken(data.token);
      this.router.navigate(['/']);
      window.location.reload();
    },
    err => {
      console.log(err.error)
      this.mensajeError = err.error;
      this.toastr.error(this.mensajeError, 'Opps', {
        timeOut: 3000,
      });
    })
  }
}
