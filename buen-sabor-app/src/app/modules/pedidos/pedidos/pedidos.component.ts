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
  ) { }

  ngOnInit(): void {
    this.emailUser = this.tokenService.getUserName()
    this.listOrder(this.emailUser);
    this.listOrder2(this.emailUser);
  }

  /**
   * @param email 
   * 
   */
  listOrder(email: string): void {
    this.authService.getDataUsuario(email).subscribe((dataUser) => {
      this.pedidoService.getPedidosByUser(dataUser.id).subscribe((dataPedido) => {
        this.pedidos = dataPedido
        this.pedidos.forEach((itemPedido) => {
          this.detallePedidoService.getDetallesPedidosByPedido(itemPedido.numeroPedido).subscribe((dataDetallePedido) => {
            dataDetallePedido.forEach((itemDetallePedido) => {
              itemPedido.total += itemDetallePedido.cantidad * itemDetallePedido.subtotal;
            })
          })
        })
      })
    });
  }
  // En desarrollo
  listOrder2(email:string): void{
    this.authService.getDataUsuario(email).pipe(
       concatMap(dataUser => { return this.pedidoService.getPedidosByUser(dataUser.id) }),
       concatMap(dataPedido => { 
         return dataPedido.map(p => {
          return this.detallePedidoService.getDetallesPedidosByPedido(p.numeroPedido)
         })
        }),
        concatMap(dataDetalle => {
          return dataDetalle.pipe()
        })
    ).subscribe(data => {
      console.log(data)
    })
  }

  showDetails(id: any): void {
    this.detallePedidoService.getDetallesPedidosByPedido(id).subscribe((data) => {
      this.detallesPedidos = data;
    })
  }

}
