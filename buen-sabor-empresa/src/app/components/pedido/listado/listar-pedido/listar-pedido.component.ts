import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { PedidoService } from 'src/app/services/pedido.service';
import { PedidoEstadoService } from 'src/app/services/pedido-estado.service';
import { DetallePedidoService } from 'src/app/services/detalle-pedido.service';

import { Pedido } from 'src/app/models/pedido';
import { DetallePedido } from 'src/app/models/detalle-pedido';

@Component({
  selector: 'app-listar-pedido',
  templateUrl: './listar-pedido.component.html',
  styleUrls: ['./listar-pedido.component.css']
})
export class ListarPedidoComponent implements OnInit {

  titulo: string = 'Listado de pedidos:';
  pedidos: Pedido[];
  detallesPedido: DetallePedido[];
  numeroPedido: number;

  constructor(private pedidoService: PedidoService, private pedidoEstadoService: PedidoEstadoService, private detallePedidoService: DetallePedidoService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getAllPedidos();
  } 

  getAllPedidos() {
    this.pedidoService.getAllPedidos().subscribe(data =>{
      this.pedidos = data.filter(item => item.pedidoEstado.id < 5);
    });
  }

  verDetalle(id: number): void {
    this.detallePedidoService.getDetalleByIdPedido(id).subscribe(data =>{
      this.detallesPedido = data;
      this.numeroPedido = data[0].pedido.numeroPedido;
    });
  }

  cambiarEstado(pedido: Pedido): void {
    let estado = pedido.pedidoEstado.id;
    if(pedido.pedidoEstado.id < 5) {
      if(pedido.tipoEnvio.id == 2) {
        estado = estado + 1;
        this.nuevoEstado(pedido, estado);
      } else {
        if(estado == 3) {
          estado = estado + 2;
          this.nuevoEstado(pedido, estado);
        } else {
          estado = estado + 1;
          this.nuevoEstado(pedido, estado);
        }
      }
    } else {
      this.getAllPedidos();
    }
  }

  nuevoEstado(pedido: Pedido, estado: number) {
    this.pedidoEstadoService.getPedidoEstadoById(estado).subscribe(data =>{
      let confirmacion = true;
      if(data.id != 6) {
        confirmacion = confirm("¿Desea cambiar el estado por " + data.denominacion + "?");
      }
      if(confirmacion) {
        pedido.pedidoEstado = data;
        this.pedidoService.saveUpdatePedido(pedido).subscribe();
      }
    });
  }

  cancelarPedido(pedido: Pedido): void {
    let confirmacion = confirm("¿Desea cancelar el pedido?");
    if(confirmacion) {
      this.nuevoEstado(pedido, 6);
    }
  }

}


