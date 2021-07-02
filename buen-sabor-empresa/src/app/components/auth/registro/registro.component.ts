import { Component, OnInit } from '@angular/core';
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
  constructor(  
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit(): void {
  }
  onRegister(): void {
    this.nuevoUsuario = new NuevoUsuario(
      this.nombre,
      this.apellido,
      this.telefono,
      this.email,
      this.clave
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
}
