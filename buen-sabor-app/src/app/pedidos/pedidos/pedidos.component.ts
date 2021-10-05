import { Component, OnInit } from '@angular/core';
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
  }

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

  showDetails(id: any): void {
    this.detallePedidoService.getDetallesPedidosByPedido(id).subscribe((data) => {
      this.detallesPedidos = data;
    })
  }

}
