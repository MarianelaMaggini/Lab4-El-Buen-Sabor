import { Component, OnInit } from '@angular/core';
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
import { concatMap } from 'rxjs/operators';
import { StorageService } from 'src/app/services/storage.service';
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
    private spinnerService: NgxSpinnerService,
    private hourSystemService: HourSystemService,
    private storageService: StorageService,
  ) {
    this.isLogged = false;
    this.isHour = true;
    this.active = false;
  }

  ngOnInit(): void {
    this.spinnerService.show();
    setTimeout(() => {
      this.getArticuloById();
      this.isLogin();
      //this.isHour = this.hourSystemService.activeSystem();
      this.spinnerService.hide();
    }, 1500)

  }
  addCart(): void {
    this.messageService.sendMessage(this.articulo);
    let inventarios = this.storageService.get('inventario');
      this.recetasElaborados.forEach(r => {
        inventarios.forEach((i: { articulo: { id: number; }; stockMinimo: number; stockActual: number; }) => {
          if (i.articulo.id === r.articulo.id) {
            i.stockActual -= r.cantidad;
            if (i.stockMinimo < i.stockActual && i.stockMinimo > r.cantidad) {
              this.active = true;
            } else {
              this.active = false;
              this.recetasElaborados.map((re) => {
                if (re.articulo.id === r.articulo.id) {
                  re.articulo.denominacion += " sin stock."
                }
              })
            }
          }
        })
      })
      
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
    this.articuloDetalleService.getArtElaboradoDetalleByArticuloId(id).pipe(
      concatMap(data => {
        this.articuloDetalle = data;
        return this.recetaService.getRecetaByArticuloDetalleId(data.id);
      }),
    ).subscribe(data => {
      let inventarios = this.storageService.get('inventario');
      this.recetasElaborados = data;
      let disponible = 0;
      let coincidencias = 0;
      let stockActualAux = 0;
      this.recetasElaborados.forEach(r => {
        inventarios.forEach((i: { articulo: { id: number; }; stockMinimo: number; stockActual: number; }) => {
          if (i.articulo.id === r.articulo.id) {
            coincidencias++;
            stockActualAux = i.stockActual;
            stockActualAux -= r.cantidad;
            if (i.stockMinimo < stockActualAux && i.stockMinimo > r.cantidad) {
              disponible++;
            } else {
              this.recetasElaborados.map((re) => {
                if (re.articulo.id === r.articulo.id) {
                  re.articulo.denominacion += " sin stock."
                }
              })
            }
          }
        })
      })
      if (disponible === coincidencias) {
        this.active = true;
      }
    });
  }

  isLogin(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
  }
}
