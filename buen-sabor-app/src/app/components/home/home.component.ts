import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Articulo } from 'src/app/models/articulo';
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
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.isAdmin = this.tokenService.isAdmin();
    this.isLogged = this.tokenService.isLogged();

    setInterval(() => {
      const date = new Date();
      this.updateDate(date);
    }, 1000)
    this.mercadoPagoDatos();
    this.activeSystem();
  }
  over() {
    const element = document.getElementById("delivery");
    element!.classList.add("animate__animated", this.animate);
    element!.classList.add("animate__animated", this.slow);
    element!.addEventListener('animationend', () => {
      element!.classList.remove("animate__animated", this.animate);
    })
  }

  updateDate(date: Date): void {
    const hours = date.getHours();
    this.ampm = hours >= 12 ? 'PM' : 'AM';
    this.hour = hours;
    this.hour = this.hour < 10 ? '0' + this.hour : this.hour;

    const minutes = date.getMinutes();
    this.minute = minutes < 10 ? '0' + minutes : minutes.toString();

  }

  activeSystem(): void {
    const time = new Date();
    if (Dia.SABADO === time.getDay() && parseInt(Horario.HORA_INICIAL_SAB_DOM) >= time.getHours() && parseInt(Horario.HORA_FINAL_SAB_DOM) < time.getHours() && parseInt(Horario.MINUTO) < time.getMinutes()) {
      this.state = 'Abierto';
    } else if (Dia.DOMINGO === time.getDay() && parseInt(Horario.HORA_INICIAL_SAB_DOM) >= time.getHours() && parseInt(Horario.HORA_FINAL_SAB_DOM) < time.getHours() && parseInt(Horario.MINUTO) < time.getMinutes()) {
      this.state = 'Abierto';
    } else if (parseInt(Horario.HORA_INICIAL) <= time.getHours() && parseInt(Horario.HORA_FINAL) < time.getHours() && parseInt(Horario.MINUTO) < time.getMinutes()) {
      this.state = 'Abierto';
    }
  }

  mercadoPagoDatos(): void {
    this.route.queryParams.subscribe((params) => {
      console.log(params)
    })
  }
}
