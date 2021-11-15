import { Component, OnDestroy, OnInit } from '@angular/core';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import { concatMap } from 'rxjs/operators';
import { DetallePedido } from 'src/app/models/detalle-pedido';
import { Factura } from 'src/app/models/factura';
import { Pedido } from 'src/app/models/pedido';
import { Usuario } from 'src/app/models/usuario';
import { AuthService } from 'src/app/services/auth.service';
import { DetallePedidoService } from 'src/app/services/detalle-pedido.service';
import { FacturaService } from 'src/app/services/factura.service';
import { PedidoService } from 'src/app/services/pedido.service';
import { PedidoEstadoService } from 'src/app/services/pedidoEstado.service';
import { TokenService } from 'src/app/services/token.service';
import * as SockJS from 'sockjs-client';
import * as Stomp from '@stomp/stompjs';

@Component({
  selector: 'app-pedidos',
  templateUrl: './pedidos.component.html',
  styleUrls: ['./pedidos.component.css']
})
export class PedidosComponent implements OnInit, OnDestroy {
  title: string;
  pedidos: Pedido[];
  facturas: Factura[];
  detallesPedidos: DetallePedido[];
  factura: Factura;
  usuario: Usuario;
  emailUser: string;
  montoDescuento: number;
  total: number;
  webSocketEndPoint: string = 'http://localhost:8080/ws';
  disabled = true;
  stompClient: any;

  constructor(
    private pedidoService: PedidoService,
    private tokenService: TokenService,
    private authService: AuthService,
    private detallePedidoService: DetallePedidoService,
    private facturaService: FacturaService,
    private pedidoEstadoService: PedidoEstadoService
  ) {
    this.title = 'Tus pedidos';
  }

  ngOnInit(): void {
    this.emailUser = this.tokenService.getUserName();
    this.listOrder(this.emailUser);
    this.connect();
  }

  ngOnDestroy(): void {
    this.disconnect();
   }

  /**
   * @param email 
   * @description Busca el pedido por el usuario y sus respectivos detalles de ese pedido
   */
  listOrder(email:string): void{
    this.authService.getDataUsuario(email).pipe(
       concatMap(dataUser => { return this.pedidoService.getPedidosByUser(dataUser.id) }),
       concatMap(dataPedido => {
        this.pedidos = dataPedido;
         return dataPedido.map(p => {
          return this.detallePedidoService.getDetallesPedidosByPedido(p.numeroPedido)
         })
        }),
        concatMap(dataDetalle => {
          return dataDetalle
        })
    ).subscribe(detallePedidos => {
      let montoDescuento = 0.1;
      this.pedidos.forEach(p => {
        detallePedidos.forEach(d => {
          if (p.numeroPedido == d.pedido.numeroPedido) {
            p.total += d.cantidad * d.subtotal;
            if (p.formaPago.indexOf('Efectivo') > -1) {
              montoDescuento *= p.total;
              p.total -= montoDescuento;
            }
          }
        })
      })
    })
  }

  showDetails(id: number): void {
      this.detallePedidoService.getDetallesPedidosByPedido(id).subscribe((data) => {
        this.detallesPedidos = data;
        
    })
  }

  getFactura(id: number): void {
    this.facturaService.getFacturaByPedidoNumeroPedido(id).pipe(
      concatMap(data => {
        this.factura = data;
        return this.detallePedidoService.getDetallesPedidosByPedido(this.factura.pedido.numeroPedido);
      })).subscribe(data =>{
        this.detallesPedidos = data;
        this.calcularMontos();
  })
}

  calcularMontos() {
    this.montoDescuento = 0;
    this.total = 0;
    this.detallesPedidos.forEach(item => {
      this.total += item.subtotal;
    });
    this.montoDescuento = this.total * this.factura.montoDescuento;
    this.total = this.total - this.montoDescuento;
  }

 generarPDF() {
    const DATA = document.getElementById('factura');
    const doc = new jsPDF('p', 'pt', 'a4');
    const options = {
      background: 'white',
      scale: 2
    };
    html2canvas((DATA)!, options).then((canvas) => {
      const img = canvas.toDataURL('image/PNG');
      // Add image Canvas to PDF
      const bufferX = 15;
      const bufferY = 15;
      const imgProps = (doc as any).getImageProperties(img);
      const pdfWidth = doc.internal.pageSize.getWidth() - 2 * bufferX;
      const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;
      doc.addImage(img, 'PNG', bufferX, bufferY, pdfWidth, pdfHeight, undefined, 'FAST');
      return doc;
    }).then((docResult) => {
      docResult.save(`${this.factura.fecha}_factura.pdf`);
    });
  }

  cancel(pedido: Pedido):void{
    this.pedidoEstadoService.getPedidoEstadoById(6).subscribe(data => {
      pedido.pedidoEstado = data;
      this.pedidoService.savePedido(pedido).subscribe();
    });
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
      console.log('Connected: ' + frame);
      this.listOrder(this.emailUser); 
      _this.stompClient.subscribe('/topic/pedido', function(data: any) {
        _this.showPedidos(data.body);
      });
    });
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
    this.listOrder(this.emailUser);        
  }

}
