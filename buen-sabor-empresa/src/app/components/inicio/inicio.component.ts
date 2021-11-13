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
  isAdmin = false;
  mensajes = [
    { texto: "¿Sabías que una hamburguesa tiene 295 calorías?" },
    { texto: "Cuándo te enteraste que el pan es la comida más comprada en el mundo"},
    { texto: "Alrededor del mundo, se consumen cinco mil millones de pizza por día. La del Buen Sabor es una de ellas."},
    { texto: "Los tres tamaños universales de pizza contienen cuatro,seis y ocho porciones, respectivamente."},
    { texto: "Se cree que la hamburguesa, como platillo con carne molida, se inventó en Hamburgo, Alemania. Esta creación se la disputan varias personas de esta ciudad."}
  ];
  mensaje: {texto: string};
  
  constructor(
    private authService: AuthService,
    private tokenService: TokenService,
    ) { }

  ngOnInit(): void {
    this.emailUsuario = this.tokenService.getUserName();
    this.getDatosUsuario();
    this.isAdmin = this.tokenService.isAdmin();
    this.mensaje = this.mensajesRandom();
  }
  
  getDatosUsuario(): void {
      this.authService.getDataUsuario(this.emailUsuario).subscribe(data => {
        this.usuario = data;
      })
  }

  mensajesRandom(){
   let i = Math.floor(Math.random()*this.mensajes.length)
   return this.mensajes[i];
  }
}
