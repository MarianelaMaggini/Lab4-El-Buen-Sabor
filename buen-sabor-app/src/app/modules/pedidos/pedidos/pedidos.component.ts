import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { concatMap, map, switchMap } from 'rxjs/operators';
import { DetallePedido } from 'src/app/models/detalle-pedido';
import { Pedido } from 'src/app/models/pedido';
import { Usuario } from 'src/app/models/usuario';
import { AuthService } from 'src/app/services/auth.service';
import { DetallePedidoService } from 'src/app/services/detalle-pedido.service';
import { PedidoService } from 'src/app/services/pedido.service';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-pedidos',
  templateUrl: './pedidos.component.html',
  styleUrls: ['./pedidos.component.css']
})
export class PedidosComponent implements OnInit {
  pedidos: Pedido[];
  pedidos$:Observable<Pedido[]>;
  detallesPedidos: DetallePedido[];
  usuario: Usuario;
  emailUser: string;
  total: number;
  constructor(
    private pedidoService: PedidoService,
    private tokenService: TokenService,
    private authService: AuthService,
    private detallePedidoService: DetallePedidoService
  ) {}

  ngOnInit(): void {
    this.emailUser = this.tokenService.getUserName()
    this.listOrder(this.emailUser);
    
  }

  /**
   * @param email 
   * @description Busca el pedido por el usuario y sus respectivos detalles de ese pedido
   */
  listOrder(email:string): void{
    this.authService.getDataUsuario(email).pipe(
       concatMap(dataUser => { return this.pedidoService.getPedidosByUser(dataUser.id) }),
       concatMap(dataPedido => {
        this.pedidos = dataPedido
         return dataPedido.map(p => {
          return this.detallePedidoService.getDetallesPedidosByPedido(p.numeroPedido)
         })
        }),
        concatMap(dataDetalle => {
          return dataDetalle
        })
    ).subscribe(detallePedidos => {
      this.pedidos.forEach(p => {
        detallePedidos.forEach(d => {
          if (p.numeroPedido == d.pedido.numeroPedido) {
            p.total += d.cantidad * d.subtotal;
          }
        })
      })
    })
  }

  showDetails(id: any): void {
      this.detallePedidoService.getDetallesPedidosByPedido(id).subscribe((data) => {
        this.detallesPedidos = data;
    })
  }
}
