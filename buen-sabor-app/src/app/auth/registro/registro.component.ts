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
  nuevoUsuario: NuevoUsuario;
  nombre: string;
  apellido: string;
  telefono: string;
  email: string;
  clave: string;
  mensajeError: string;
  isActive: boolean = false;

  // Formulario para nuevo usuario
  nuevo = new FormGroup({
    nombre: new FormControl('', [
      Validators.required, 
      Validators.minLength(4), 
      Validators.maxLength(20)]),
    apellido: new FormControl('', [
      Validators.required, 
      Validators.minLength(4),
      Validators.maxLength(20)]),
    telefono: new FormControl('', [
      Validators.required,]),   
    email: new FormControl('', [
      Validators.required, 
      Validators.email]),
    clave: new FormControl('', [
      Validators.required,
      Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}')]),
  })
  constructor( 
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit(): void {
  }
  onRegister(form: NuevoUsuario): void {
    this.nuevoUsuario = new NuevoUsuario(
      form.nombre,
      form.apellido,
      form.telefono,
      form.email,
      form.clave
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
        this.mensajeError = err.error.message;
        this.toastr.error(this.mensajeError, 'Opps', {
          timeOut: 3000,
        });
        console.log(err);
      }
    );
  }

  get f(){
    return this.nuevo.controls;
  }

}
