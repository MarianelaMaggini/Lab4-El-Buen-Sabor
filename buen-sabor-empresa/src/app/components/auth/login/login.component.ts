import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
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
  // Formulario para login
  login = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    clave: new FormControl('', [Validators.required]),
  })
  mensajeError: string;
  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
  }
  onLogin(form: LoginUsuario): void {
    this.loginUsuario = new LoginUsuario(form.email, form.clave);
    this.authService.login(this.loginUsuario).subscribe(data => {
      this.tokenService.setToken(data.token);
      window.location.href = '/'
    },
    err => {
      console.log(err.error)
      this.mensajeError = err.error;
      this.toastr.error(this.mensajeError, 'Opps', {
        timeOut: 3000,
      });
    })
  }
  get f(){
    return this.login.controls;
  }
}
