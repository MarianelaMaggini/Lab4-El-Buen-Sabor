import { Component, Input, OnInit , Output , EventEmitter} from '@angular/core';
import { Router } from '@angular/router';
import { SocialUser } from 'angularx-social-login';
import { Articulo } from 'src/app/models/articulo';
import { Horario } from 'src/app/utils/horario';
import { Tiempo } from 'src/app/models/tiempo';
import { MessageService } from 'src/app/services/message.service';
import { TiempoService } from 'src/app/services/tiempo.service';
import { TokenService } from 'src/app/services/token.service';
import { Dia } from 'src/app/utils/dia';


@Component({
  selector: 'app-item-articulo',
  templateUrl: './item-articulo.component.html',
  styleUrls: ['./item-articulo.component.css']
})
export class ItemArticuloComponent implements OnInit {

  @Input() articulo: Articulo;
  @Input() index: number;
  @Output() articuloSeleccionado: EventEmitter<number>;
  isLogged = false;
  userLogged: SocialUser;
  tiempo: Tiempo;
  isHour = false;

  constructor(
    private messageService: MessageService,
    private tokenService: TokenService,
    private router: Router,
    private tiempoService: TiempoService,
    ) { 
      this.articuloSeleccionado = new EventEmitter();
    }
  

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
   }
  
 /*  addCart():void{
    this.activeSystem();
  }

  addCart(): void {
    this.messageService.sendMessage(this.articulo);
  } */

  detail():void{
    this.articuloSeleccionado.emit(this.index);
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
