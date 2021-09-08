import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SocialUser } from 'angularx-social-login';
import { ArticuloElaboradoDetalle } from 'src/app/models/articulo-elaborado-detalle';
import { Tiempo } from 'src/app/models/tiempo';
import { ArticuloElaboradoDetalleService } from 'src/app/services/articulo-elaborado-detalle.service';
import { MessageService } from 'src/app/services/message.service';
import { RecetaElaboradoService } from 'src/app/services/receta-elaborado.service';
import { TiempoService } from 'src/app/services/tiempo.service';
import { TokenService } from 'src/app/services/token.service';
import { Dia } from 'src/app/utils/dia';
import { Horario } from 'src/app/utils/horario';
import { Articulo } from '../../models/articulo';
import { RecetaElaborado} from '../../models/receta-elaborado';
import { ArticuloService } from '../../services/articulo.service';

@Component({
  selector: 'app-articulo-detalle',
  templateUrl: './articulo-detalle.component.html',
  styleUrls: ['./articulo-detalle.component.css'],
})
export class ArticuloDetalleComponent implements OnInit {
  articulo: Articulo;
  articuloDetalle: ArticuloElaboradoDetalle;
  recetasElaborados: RecetaElaborado[];
  userLogged: SocialUser;
  tiempo: Tiempo;
  id: number;
  imagen: string;
  idDetalle: number;
  isLogged = false;
  isHour = false;

  constructor(
    private articuloService: ArticuloService,
    private route: ActivatedRoute,
    private articuloDetalleService: ArticuloElaboradoDetalleService,
    private recetaService: RecetaElaboradoService,
    private messageService: MessageService,
    private tokenService: TokenService,
    private tiempoService: TiempoService,
  ) {}

  ngOnInit(): void {
    this.getArticuloById();
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
    this.activeSystem();
  }
  addCart(): void {
    this.messageService.sendMessage(this.articulo);
  } 
  getArticuloById(): void {
    this.id = this.route.snapshot.params['id'];
    this.articuloService.getArticuloById(this.id).subscribe((articulo) => {
      console.log(articulo)
      this.articulo = articulo;
      this.imagen =
        'http://localhost:8080/upload/files/' + this.articulo.imagen;
        if (articulo.tipoArticulo.id == 2) {
          this.listDetalleAndReceta(articulo.id);
        }
    });
  }

  listDetalleAndReceta(id:number):void{
    this.articuloDetalleService.getArtElaboradoDetalleByArticuloId(id).subscribe((detalle) =>{
      this.articuloDetalle = detalle;
      this.recetaService.getRecetaByArticuloDetalleId(detalle.id).subscribe((receta)=>{
        this.recetasElaborados = receta;
        })
    })
  }

  activeSystem(): void {
    this.tiempoService.getTiempo().subscribe((data) => {
      this.tiempo = data;
      if (Dia.SABADO === this.tiempo.diaNumero && parseInt(Horario.HORA_INICIAL_SAB_DOM) >= this.tiempo.hora && parseInt(Horario.HORA_FINAL_SAB_DOM) < this.tiempo.hora && parseInt(Horario.MINUTO) < this.tiempo.minuto) {
        this.isHour = true;
      } else if (Dia.DOMINGO === this.tiempo.diaNumero && parseInt(Horario.HORA_INICIAL_SAB_DOM) >= this.tiempo.hora && parseInt(Horario.HORA_FINAL_SAB_DOM) < this.tiempo.hora && parseInt(Horario.MINUTO) < this.tiempo.minuto) {
        this.isHour = true;
      } else if (parseInt(Horario.HORA_INICIAL) <= this.tiempo.hora && parseInt(Horario.HORA_FINAL) < this.tiempo.hora && parseInt(Horario.MINUTO) < this.tiempo.minuto) {
        this.isHour = true;
      }
    })
  }
}
