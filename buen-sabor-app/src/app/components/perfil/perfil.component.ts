import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl } from '@angular/forms';
import { Domicilio } from 'src/app/models/domicilio';
import { Usuario } from 'src/app/models/usuario';
import { AuthService } from 'src/app/services/auth.service';
import { DomicilioService } from 'src/app/services/domicilio.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css'],
})
export class PerfilComponent implements OnInit {

  titulo: string = 'Domicilios';
  editable: boolean = false;
  domicilios: Domicilio[];
  usuario: Usuario;
  emailUser: string;

  
  usuarioForm = new FormGroup({

    nombre: new FormControl(''),
    apellido: new FormControl(''),
    email: new FormControl(''),
    telefono: new FormControl('')
   
  })

  constructor(
    private domicilioService: DomicilioService,
    private authService: AuthService,
    private tokenService: TokenService,
  ) {}

  ngOnInit(): void {
    this.emailUser = this.tokenService.getUserName();
    this.getUser(this.emailUser);
    this.getDomicilios();
  }

  getDomicilios() {
    this.domicilioService.getDomicilios().subscribe((data) => {
      this.domicilios = data;
    });
  }

  getUser(email: string): void {
    this.authService.getDataUsuario(email).subscribe((data) => {
      this.usuarioForm.setValue({
      'nombre': data.nombre,
      'apellido': data.apellido,
      'email': data.email,
      'telefono': data.telefono
      })
    });
  }
  
}
