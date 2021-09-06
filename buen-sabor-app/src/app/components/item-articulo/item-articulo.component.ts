import { Component, Input, OnInit , Output , EventEmitter} from '@angular/core';
import { Router } from '@angular/router';
import { SocialUser } from 'angularx-social-login';
import { Articulo } from 'src/app/models/articulo';
import { MessageService } from 'src/app/services/message.service';
import { TokenService } from 'src/app/services/token.service';


@Component({
  selector: 'app-item-articulo',
  templateUrl: './item-articulo.component.html',
  styleUrls: ['./item-articulo.component.css']
})
export class ItemArticuloComponent implements OnInit {

  @Input() articulo: Articulo;
  @Input() index: number;
  isLogged = false;
  userLogged: SocialUser;
  @Output() articuloSeleccionado: EventEmitter<number>;

  constructor(
    private messageService: MessageService,
    private tokenService: TokenService,
    private router: Router
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
    this.messageService.sendMessage(this.articulo);
  } */

  detail():void{
    this.articuloSeleccionado.emit(this.index);
  }
}
