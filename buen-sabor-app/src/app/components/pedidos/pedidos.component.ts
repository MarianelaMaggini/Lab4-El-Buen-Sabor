import {  Component, OnInit } from '@angular/core';
import { Pedido } from 'src/app/models/pedido';
import { PedidoService } from 'src/app/services/pedido.service';

@Component({
  selector: 'app-pedidos',
  templateUrl: './pedidos.component.html',
  styleUrls: ['./pedidos.component.css']
})
export class PedidosComponent implements OnInit {
  pedidos: Pedido[];
  constructor(private pedidoService: PedidoService) { }

  ngOnInit(): void {
    this.listarPedidos();
  }

  listarPedidos():void {
    this.pedidoService.getTiposEnvios().subscribe((data) => {
      this.pedidos =data
    })
  }

}
