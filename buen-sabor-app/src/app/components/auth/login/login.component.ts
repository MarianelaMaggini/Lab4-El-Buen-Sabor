import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SocialAuthService, SocialUser } from "angularx-social-login";
import {  GoogleLoginProvider } from "angularx-social-login";
import { ToastrService } from 'ngx-toastr';
import { JwtDto } from 'src/app/models/jwt-dto';
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
  mensajeError: string;
  socialUser: SocialUser;
  userLogged: SocialUser;
  isLogged: boolean;

  // Formulario para login
  login = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    clave: new FormControl('', [Validators.required]),
  })
  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private socialAuthService: SocialAuthService,
    private toastr: ToastrService
  ) { }
  
  ngOnInit(): void {
    console.log(this.f)
    this.socialAuthService.authState.subscribe((data) => {
      this.userLogged = data;
      this.isLogged = (this.userLogged != null && this.tokenService.getToken() != null)
    })
  }
  
  get f(){
    return this.login.controls;
  }

  onLogin(form: LoginUsuario): void {
    this.loginUsuario = new LoginUsuario(form.email, form.clave);
    this.authService.login(this.loginUsuario).subscribe(data => {
      this.tokenService.setToken(data.token);
      window.location.reload();
    },
    err => {
      console.log(err.error)
      this.mensajeError = err.error.message;
      this.toastr.error(this.mensajeError, 'Opps', {
        timeOut: 3000,
      });
    })
  }

  signInWithGoogle(): void {
    this.socialAuthService.signIn(GoogleLoginProvider.PROVIDER_ID).then(
      data => {
        this.socialUser = data;
        const tokenGoogle = new JwtDto(this.socialUser.idToken)
        console.log(this.socialUser.idToken);
        this.authService.loginWithGoogle(tokenGoogle).subscribe(
          res => {
            this.tokenService.setToken(res.token);
            this.isLogged = true;
            window.location.reload();
          }, 
          err => {
            console.log(err);
            this.tokenService.logOut();
          }
        )
      }
    ).catch(
      err => {
        console.log(err)
      }
    )
  }

}
