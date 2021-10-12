import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { PedidoService } from 'src/app/services/pedido.service';
import { PedidoEstadoService } from 'src/app/services/pedido-estado.service';
import { DetallePedidoService } from 'src/app/services/detalle-pedido.service';

import { Pedido } from 'src/app/models/pedido';
import { DetallePedido } from 'src/app/models/detalle-pedido';
import { TokenService } from 'src/app/services/token.service';

import * as SockJS from 'sockjs-client';
import * as Stomp from '@stomp/stompjs';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-listar-pedido',
  templateUrl: './listar-pedido.component.html',
  styleUrls: ['./listar-pedido.component.css']
})
export class ListarPedidoComponent implements OnInit, OnDestroy {

  titulo: string = 'Listado de pedidos:';
  pedidos: Pedido[];
  detallesPedido: DetallePedido[];
  numeroPedido: number;
  isChef: boolean = false;
  isCashier: boolean = false;
  isAdmin: boolean = false;
  webSocketEndPoint: string = 'http://localhost:8080/ws';
  disabled = true;
  stompClient: any;

  constructor(
    private pedidoService: PedidoService, 
    private pedidoEstadoService: PedidoEstadoService, 
    private detallePedidoService: DetallePedidoService, 
    private activatedRoute: ActivatedRoute, 
    private tokenService: TokenService, 
    private toastrService: ToastrService
    ) { }
  
  ngOnInit(): void {
    this.isAdmin = this.tokenService.isAdmin();
    this.isChef = this.tokenService.isChef();
    this.isCashier = this.tokenService.isCashier();
    this.getAllPedidos();
    this.connect();
  }
  
  ngOnDestroy(): void {
   this.disconnect();
  }

  getAllPedidos() {
    this.pedidoService.getAllPedidos().subscribe(data =>{
      console.log(data)
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

  setConnected(connected: boolean) {
    this.disabled = !connected;

    if (connected) {
      this.pedidos = [];
    }
  }

  connect() {
    const socket = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(socket);
    const _this = this;
    _this.stompClient.connect({},  (frame: any) => {
      this.setConnected(true);
      console.log('Connected: ' + frame)
      this.getAllPedidos();
      _this.stompClient.subscribe('/topic/pedido', function(data: any) {
        _this.showPedidos(data.body);
      })

      _this.stompClient.subscribe('/topic/mensaje', function(message: any){
        console.log(message.body)
        _this.showMessage(JSON.parse(message.body).message)
      })
    })
  }

  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }

    this.setConnected(false);
    console.log('Disconnected!');
  }

  showPedidos(pedido: Pedido) {
    this.pedidos.push(JSON.parse(pedido.toString()));
    this.getAllPedidos();        
  }

  showMessage(message: string){
    this.reproducir();
    this.toastrService.info("<h5>"+message+"</h5>", 'Hola', {
      timeOut: 4000,
      positionClass: 'toast-top-right',
      closeButton:true,
      progressBar: true,
      enableHtml: true
    })
  }

  reproducir():void{
    const audio = new Audio('assets/notification.mp3')
    audio.play();
  }

}


