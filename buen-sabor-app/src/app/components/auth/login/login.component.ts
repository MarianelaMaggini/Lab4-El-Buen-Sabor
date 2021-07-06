import { Component, OnInit } from '@angular/core';
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
  email: string;
  clave: string;
  mensajeError: string;
  socialUser: SocialUser;
  userLogged: SocialUser;
  isLogged: boolean;
  constructor(
    private tokenService: TokenService,
    private authService: AuthService,
    private socialAuthService: SocialAuthService,
    private toastr: ToastrService
  ) { }

  ngOnInit(): void {
    this.socialAuthService.authState.subscribe((data) => {
      this.userLogged = data;
      this.isLogged = (this.userLogged != null && this.tokenService.getToken() != null)
    })
  }
  onLogin(): void {
    this.loginUsuario = new LoginUsuario(this.email, this.clave);
    this.authService.login(this.loginUsuario).subscribe(data => {
      this.tokenService.setToken(data.token);
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
