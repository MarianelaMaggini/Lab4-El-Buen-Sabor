import { Component,  OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SocialUser } from 'angularx-social-login';
import { NgxSpinnerService } from 'ngx-spinner';
import { ArticuloElaboradoDetalle } from 'src/app/models/articulo-elaborado-detalle';
import { ArticuloElaboradoDetalleService } from 'src/app/services/articulo-elaborado-detalle.service';
import { HourSystemService } from 'src/app/services/hour-system.service';
import { MessageService } from 'src/app/services/message.service';
import { RecetaElaboradoService } from 'src/app/services/receta-elaborado.service';
import { TokenService } from 'src/app/services/token.service';
import { Articulo } from 'src/app/models/articulo';
import { RecetaElaborado } from 'src/app/models/receta-elaborado';
import { ArticuloService } from 'src/app/services/articulo.service';
import { InventarioService } from 'src/app/services/inventario.service';
@Component({
  selector: 'app-articulo-detalle',
  templateUrl: './articulo-detalle.component.html',
  styleUrls: ['./articulo-detalle.component.css'],
})
export class ArticuloDetalleComponent implements OnInit {
  articulo: Articulo;
  articuloDetalle: ArticuloElaboradoDetalle;
  recetasElaborados: RecetaElaborado[] = [];
  userLogged: SocialUser;
  id: number;
  imagen: string;
  idDetalle: number;
  isLogged: boolean;
  isHour: boolean;
  active: boolean;
  constructor(
    private articuloService: ArticuloService,
    private route: ActivatedRoute,
    private articuloDetalleService: ArticuloElaboradoDetalleService,
    private recetaService: RecetaElaboradoService,
    private messageService: MessageService,
    private tokenService: TokenService,
    private inventarioService: InventarioService,
    private spinnerService: NgxSpinnerService,
    private hourSystemService: HourSystemService
  ) {
    this.isLogged = false;
    this.isHour = false;
    this.active = false;
  }

  ngOnInit(): void {
    this.spinnerService.show();
    setTimeout(() => {
      this.getArticuloById();
      this.isLogin();
      this.isHour = this.hourSystemService.activeSystem();
      this.spinnerService.hide();
     }, 1500)
    
  }
  addCart(): void {
    this.messageService.sendMessage(this.articulo);
  }
  getArticuloById(): void {
    this.id = this.route.snapshot.params['id'];
    this.articuloService.getArticuloById(this.id).subscribe((articulo) => {
      this.articulo = articulo;
      this.imagen =
        'http://localhost:8080/upload/files/' + this.articulo.imagen;
      if (articulo.tipoArticulo.id == 2) {
        this.listDetalleAndRecetaAndInventarios(articulo.id);
      }
      if (articulo.tipoArticulo.id == 3) {
        this.active = true;
      }
    });
  }

  listDetalleAndRecetaAndInventarios(id: number): void {
    this.articuloDetalleService.getArtElaboradoDetalleByArticuloId(id).subscribe((detalles) => {
      this.articuloDetalle = detalles;
      this.recetaService.getRecetaByArticuloDetalleId(detalles.id).subscribe((recetas) => {
        this.recetasElaborados = recetas;
        
        this.inventarioService.getInventarios().subscribe((inventarios) => {
          let disponible = 0;
          let coincidencias = 0;
          inventarios.forEach(i => {
            this.recetasElaborados.forEach(r => {
              if (i.articulo.id === r.articulo.id) {
                coincidencias += 1;
                if (i.stockMinimo < i.stockActual && i.stockMinimo > r.cantidad) {
                  disponible += 1;
                }else{
                  this.recetasElaborados.map((re) => {
                    if(re.articulo.id === r.articulo.id){
                      re.articulo.denominacion += " sin stock." 
                    }
                  })
                }  
              }
            })
          })
        
          if (disponible === coincidencias ) {
            this.active = true;
          }
      })
    })
  })
}

  isLogin():void{
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
  }

}
