import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Articulo } from 'src/app/models/articulo';
import { HourSystemService } from 'src/app/services/hour-system.service';
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
  animate: string;
  slow: string;
  fechaActual: Date;
  state: string;

  constructor(
    private tokenService: TokenService,
    private route: ActivatedRoute,
    private hourSystemService: HourSystemService
  ) {
    this.state = 'Cerrado';
    this.animate = 'animate__bounceOutRight';
    this.slow = 'animate__slow';
  }

  ngOnInit(): void {
    this.isAdmin = this.tokenService.isAdmin();
    this.isLogged = this.tokenService.isLogged();

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
}
