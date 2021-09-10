import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { NuevoUsuario } from 'src/app/models/nuevo-usuario';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {

  nuevoUsuarioForm = new FormGroup({
    nombre: new FormControl('', [Validators.required]),
    apellido: new FormControl('', [Validators.required]),
    telefono:new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    clave: new FormControl('', [Validators.required]),
  });
  nuevoUsuario: NuevoUsuario;
  roles: string[] = [];
  rol: string;
  mensajeError: string;
  constructor(  
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit(): void {
  }

  get f(){
    return this.nuevoUsuarioForm.controls;
  }
  onRegister(form: NuevoUsuario): void {
    this.roles.push(this.rol);
    this.nuevoUsuario = new NuevoUsuario(
      form.nombre,
      form.apellido,
      form.telefono,
      form.email,
      form.clave,
      this.roles
    );
    console.log(this.nuevoUsuario)
    this.authService.nuevo(this.nuevoUsuario).subscribe(
      (data) => {
        console.log(data)
        this.toastr.success('Cuenta creada', 'OK', {
          timeOut: 3000,
        });
        this.router.navigate(['/login']);
      },

      (err) => {
        this.mensajeError = err.error;
        this.toastr.error(this.mensajeError, 'Opps', {
          timeOut: 3000,
        });
        console.log(err);
      }
    );
  }

  rolesChange(event:any):void{
    this.rol = event.target.value;
    console.log(event.target.value)
  }
}
