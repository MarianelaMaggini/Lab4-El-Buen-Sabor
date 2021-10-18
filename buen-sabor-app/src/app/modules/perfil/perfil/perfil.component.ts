import { Component, OnInit  } from '@angular/core';
import { FormGroup,FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { concatMap } from 'rxjs/operators';
import { Domicilio } from 'src/app/models/domicilio';
import { DomicilioForm } from 'src/app/models/domicilio-form';
import { Localidad } from 'src/app/models/localidad';
import { Usuario } from 'src/app/models/usuario';
import { AuthService } from 'src/app/services/auth.service';
import { DomicilioService } from 'src/app/services/domicilio.service';
import { LocalidadService } from 'src/app/services/localidad.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css'],
})
export class PerfilComponent implements OnInit {
  

  titulo: string = 'Domicilios';
  editable: boolean = false;
  localidades: Localidad[];
  domicilios: Domicilio[];
  usuario: Usuario;
  emailUser: string;
  formAddress: FormGroup;
  usuarioForm: FormGroup;
  openForm: boolean;
  constructor(
    private domicilioService: DomicilioService,
    private authService: AuthService,
    private tokenService: TokenService,
    private localidadService: LocalidadService,
    private router: Router
  ) {

    this.openForm = false;
  /** Formulario para usuario */
    this.usuarioForm = new FormGroup({
      id: new FormControl(''),
      nombre: new FormControl(''),
      apellido: new FormControl(''),
      email: new FormControl(''),
      telefono: new FormControl('')
    })

    /** Formulario para domicilio */
    this.formAddress = new FormGroup({
      id: new FormControl(''),
      calle: new FormControl('', [Validators.required]),
      numero: new FormControl('', [Validators.required, Validators.maxLength(4)]),
      idLocalidad: new FormControl('', [Validators.required]),
      fechaBaja: new FormControl(''),
      usuario: new FormControl('')
    })
  }

  ngOnInit(): void {
    this.emailUser = this.tokenService.getUserName();
    this.getUser(this.emailUser);
  }

  listAddress(id: number) {
    this.domicilioService.getDomicilioByUserId(id).subscribe((data) => {
      this.domicilios = data;
    })
  }

  getUser(email: string): void {
    this.authService.getDataUsuario(email).subscribe((data) => {
      this.usuario = data;
      this.listAddress(data.id);
      this.usuarioForm.setValue({
      'id': data.id,
      'nombre': data.nombre,
      'apellido': data.apellido,
      'email': data.email,
      'telefono': data.telefono
      })
    });
  }

  updateUser(form: Usuario):void{
    let usuario: Usuario = {"id": form.id, "nombre": form.nombre, "apellido": form.apellido, "telefono":form.telefono, "email": form.email, "clave": this.usuario.clave, "enabled":this.usuario.enabled, "tokenPassword":this.usuario.tokenPassword}
    this.authService.updateUsuario(usuario).subscribe(data => {
      console.log(data);
      this.router.navigate(['/'])
    });
  }

  /**
   * @description Listado de localidades
   */
   listLocalities(): void {
    this.localidadService.getLocalidades().subscribe((localidad) => {
      this.localidades = localidad;
    })
  }

  /**
   * @param form 
   * @description Mediante petición post envía un domicilio al servidor para persistirlo
   */
   saveNewAddress(form: DomicilioForm): void {
    this.localidadService.getLocalidadById(form.idLocalidad).pipe(
      concatMap(dataLocalidad => {
        let domicilio = { "id": 0, "calle": form.calle, "numero": form.numero, "localidad": dataLocalidad, "fechaBaja": null, "usuario": this.usuario };
        return this.domicilioService.saveDomicilio(domicilio)
      }),
    ).subscribe(data => {
      this.domicilios.push(data);
    });
  }

  /**
   * @param form
   * @description Mediante petición post envía un domicilio existente al servidor para persistirlo
   * lo elimina de manera lógica por fecha
   */
  deleteAddress(id: number): void {
    this.domicilios = this.domicilios.filter((item) => item.id !== id);
    this.domicilioService.getDomicilioById(id).pipe(
      concatMap(dataDomicilio => {
        let domicilio = { "id": dataDomicilio.id, "calle": dataDomicilio.calle, "numero": dataDomicilio.numero, "localidad": dataDomicilio.localidad, "fechaBaja": new Date(), "usuario": this.usuario };
        return this.domicilioService.saveDomicilio(domicilio);
      }),
    ).subscribe();
  }
  
  localitiesInModal():void{
      this.listLocalities();
  }

  deleteDomicilio(id:number):void{
    this.domicilios = this.domicilios.filter((item) => item.id !== id);
    this.domicilioService.getDomicilioById(id).pipe(
      concatMap(dataDomicilio => {
        let domicilio = { "id": dataDomicilio.id, "calle": dataDomicilio.calle, "numero": dataDomicilio.numero, "localidad": dataDomicilio.localidad, "fechaBaja": new Date(), "usuario": this.usuario };
        return this.domicilioService.saveDomicilio(domicilio);
      }),
    ).subscribe();
  }
    
}
