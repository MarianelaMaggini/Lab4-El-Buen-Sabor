import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { SocialUser } from 'angularx-social-login';
import { Articulo } from 'src/app/models/articulo';
import { TokenService } from 'src/app/services/token.service';


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


  constructor(
    private tokenService: TokenService,
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

  detail(): void {
    // this.router.navigate(['/detalle', this.index])
    this.articuloSeleccionado.emit(this.index);
  }

}
