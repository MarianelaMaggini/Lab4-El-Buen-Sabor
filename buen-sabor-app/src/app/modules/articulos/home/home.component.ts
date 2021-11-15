import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Articulo } from 'src/app/models/articulo';
import { Inventario } from 'src/app/models/inventario';
import { HourSystemService } from 'src/app/services/hour-system.service';
import { InventarioService } from 'src/app/services/inventario.service';
import { StorageService } from 'src/app/services/storage.service';
import { TokenService } from 'src/app/services/token.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  articulos: Articulo[];
  inventarios: Inventario[]
  isLogged = false;
  isAdmin = false;
  animate: string;
  slow: string;
  fechaActual: Date;
  state: string;

  constructor(
    private tokenService: TokenService,
    private route: ActivatedRoute,
    private hourSystemService: HourSystemService,
    private inventarioService: InventarioService,
    private storageService: StorageService
  ) {
    this.state = 'Cerrado';
    this.animate = 'animate__bounceOutRight';
    this.slow = 'animate__slow';
  }

  ngOnInit(): void {
    this.isAdmin = this.tokenService.isAdmin();
    this.isLogged = this.tokenService.isLogged();
    this.setInventariosInStorage();
    setInterval(() => { this.fechaActual = new Date(); }, 1000)
    this.mercadoPagoDatos();
    this.isOpenOrClose();
  }
  over() {
    const element = document.getElementById("delivery");
    element!.classList.add("animate__animated", this.animate);
    element!.classList.add("animate__animated", this.slow);
    element!.addEventListener('animationend', () => {
      element!.classList.remove("animate__animated", this.animate);
    })
  }

  isOpenOrClose(): void {
    if (this.hourSystemService.activeSystem()) {
      this.state = 'Abierto';
    }
  }

  mercadoPagoDatos(): void {
    this.route.queryParams.subscribe((params) => {
      console.log(params)
    })
  }

  setInventariosInStorage():void{
    if (this.isLogged) {
      this.inventarioService.getInventarios().subscribe(data => {
        this.inventarios = data;
        if (!this.storageService.exist('inventario')) {
          this.storageService.set('inventario', this.inventarios);
        }
      })
    }else{
      this.storageService.clear('inventario')
      this.storageService.clear('quantity')
    }
  }

  announcement():void{
    Swal.fire({
      icon: 'info',
      html: 
      '<p>La aplicación fue realizada con fines educativos por alumnos de la Universidad Tecnológica Nacional de Mendoza Argentina.</p>'+
      '<p>La misma es una demo, pero perfectamente puede ser usada de manera comercial bajo las normativas de comercialización y distribución de cada país.</p>'+
      '<p>El proceso de pago con Mercado Pago solo es de prueba, por favor, no introduzca sus valores reales.</p>'+
      '<p>Puede saber más del buen sabor <a href="https://github.com/MarianelaMaggini/Lab4-El-Buen-Sabor">aquí</a></p>',
      width: 600,
      padding: '3em',
      backdrop: `
        rgba(0,0,123,0.4)
        url("../../../../assets/img/burguer.gif")
        left top
        no-repeat
      `
    })
  }
}
