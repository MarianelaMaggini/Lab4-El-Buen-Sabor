import { Component, OnInit } from '@angular/core';
import { Articulo } from 'src/app/models/articulo';
import { Tiempo } from 'src/app/models/tiempo';
import { ArticuloService } from 'src/app/services/articulo.service';
import { TiempoService } from 'src/app/services/tiempo.service';
import { TokenService } from 'src/app/services/token.service';
import { Dia } from 'src/app/utils/dia';
import { Horario } from 'src/app/utils/horario';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  articulos: Articulo[];
  tiempo: Tiempo;
  isLogged = false;
  isAdmin = false;
  animate: string = "animate__bounceOutRight"; 
  slow: string = "animate__slow"
  day: string;
  hour: any;
  minute: string;
  second: string;
  ampm: string;
  state = 'Cerrado';
  constructor(
    private tokenService: TokenService,
    private tiempoService: TiempoService,
    ) { }

  ngOnInit(): void {
    this.isAdmin = this.tokenService.isAdmin();
    this.isLogged = this.tokenService.isLogged();
    
    setInterval(() => {
      const date = new Date();
      this.updateDate(date);
    }, 1000)

    this.activeSystem();
  }
  over(){
    const element = document.getElementById("delivery");
    element!.classList.add("animate__animated", this.animate);
    element!.classList.add("animate__animated", this.slow);
    element!.addEventListener('animationend', () => {
      element!.classList.remove("animate__animated", this.animate);
    })
  }

  updateDate(date: Date):void{
    const hours = date.getHours();
    this.ampm = hours >= 12 ? 'PM' : 'AM';
    this.hour = hours;
    this.hour = this.hour < 10 ? '0' + this.hour : this.hour;

    const minutes = date.getMinutes();
    this.minute = minutes < 10 ? '0' + minutes : minutes.toString();

  }
  

  activeSystem(): void {
    this.tiempoService.getTiempo().subscribe((data) => {
      this.tiempo = data;
      if (Dia.SABADO === this.tiempo.diaNumero && parseInt(Horario.HORA_INICIAL_SAB_DOM) >= this.tiempo.hora && parseInt(Horario.HORA_FINAL_SAB_DOM) < this.tiempo.hora && parseInt(Horario.MINUTO) < this.tiempo.minuto) {
        this.state = 'Abierto';
      } else if (Dia.DOMINGO === this.tiempo.diaNumero && parseInt(Horario.HORA_INICIAL_SAB_DOM) >= this.tiempo.hora && parseInt(Horario.HORA_FINAL_SAB_DOM) < this.tiempo.hora && parseInt(Horario.MINUTO) < this.tiempo.minuto) {
        this.state = 'Abierto';
      } else if (parseInt(Horario.HORA_INICIAL) <= this.tiempo.hora && parseInt(Horario.HORA_FINAL) < this.tiempo.hora && parseInt(Horario.MINUTO) < this.tiempo.minuto) {
        this.state = 'Abierto';
      }
    })
  }
}
