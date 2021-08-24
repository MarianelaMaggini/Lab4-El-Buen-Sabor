import { getLocaleDayPeriods } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Articulo } from 'src/app/models/articulo';
import { DetallePedido } from 'src/app/models/detalle-pedido';
import { Domicilio } from 'src/app/models/domicilio';
import { PedidoEstado } from 'src/app/models/pedido-estado';
import { TipoEnvio } from 'src/app/models/tipo-envio';
import { Usuario } from 'src/app/models/usuario';
import { AuthService } from 'src/app/services/auth.service';
import { DomicilioService } from 'src/app/services/domicilio.service';
import { MercadoPagoService } from 'src/app/services/mercado-pago.service';
import { MessageService } from 'src/app/services/message.service';
import { PedidoService } from 'src/app/services/pedido.service';
import { PedidoEstadoService } from 'src/app/services/pedidoEstado.service';
import { StorageService } from 'src/app/services/storage.service';
import { TipoEnvioService } from 'src/app/services/tipo-envio.service';
import { TokenService } from 'src/app/services/token.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {

  // atributos
  cartItems: any = [];
  tiposEnvios: TipoEnvio[];
  domicilios: Domicilio[];
  formasPago: any = ['Efectivo', 'Mercado Pago'];
  idTipoEnvio: number = 0;
  idFormaPago: number;
  idDomicilio: number = 0;
  total: number = 0;
  usuario: Usuario;
  tipoEnvio: TipoEnvio;
  domicilio: Domicilio;
  pedidoEstado: PedidoEstado;


  // constructor
  constructor(
    private messageService: MessageService,
    private storageService: StorageService,
    private mercadoPagoService: MercadoPagoService,
    private tipoEnvioService: TipoEnvioService,
    private domicilioService: DomicilioService,
    private pedidoEstadoService: PedidoEstadoService,
    private pedidoService: PedidoService,
    private tokenService: TokenService,
    private authService: AuthService,
  ) { }

  ngOnInit(): void {
    this.getUser();
    if (this.storageService.existCart()) {
      this.cartItems = this.storageService.getCart();
    }
    this.getItem();
    this.total = this.getTotal();
  }

  /**
   * Método void para recargar la página
   */
  refresh(): void {
    location.reload();
  }

  /**
   * Método void que obtiene el articulo y lo añade al carrito
   */
  getItem(): void {
    this.messageService.getMessage().subscribe((articulo: Articulo) => {
      let exist = false;
      this.cartItems.forEach((item: { id: number; cantidad: number }) => {
        if (item.id === articulo.id) {
          exist = true;
          item.cantidad++;
        }
      });
      if (!exist) {
        const cartItem = new DetallePedido(articulo);
        this.cartItems.push(cartItem);
      }
      this.total = this.getTotal();
      this.storageService.setCart(this.cartItems);
    });
  }
  /**
   * Este método itera sobre los items del carrito y suma al total el precio por la cantidad de cada item
   * @returns el precio total del carrito
   */
  getTotal(): number {
    let total = 0;
    this.cartItems.forEach((item: { cantidad: number; precio: number }) => {
      total += item.cantidad * item.precio;
    });
    return +total.toFixed(2);
  }

  /**
   * Método void que limpia el carrito completamente
   */
  emptyCart(): void {
    this.cartItems = [];
    this.total = 0;
    this.storageService.clear();
  }

  /**
   * Elimina el item reduciendo la cantidad
   * @param i Recibe por parametro el indice del item 
   */
  deleteItem(i: number): void {
    if (this.cartItems[i].cantidad > 1) {
      this.cartItems[i].cantidad--;
    } else {
      this.cartItems.splice(i, 1);
    }
    this.total = this.getTotal();
    this.storageService.setCart(this.cartItems);
  }

  /**
   * Método void que finaliza la compra dependiente de la forma de pago
   */
  finalizarCompra(): void {
    if (this.idFormaPago == 0) {
      Swal.fire({
        title: 'Pedido realizado con éxito',
        icon: 'success',
        text: 'Gracias por confiar en el Buen Sabor 🍕',
      });
      if (this.idTipoEnvio == 1) {
        this.getUserBuenSabor("bsabor2021@gmail.com");
        let id = this.usuario.id
        this.getTipoEnvio();
        this.getPedidoEstado(1);
        this.getDomicilio(id)
      }else{
        this.getUser();
        this.getTipoEnvio();
        this.getPedidoEstado(1);
        this.getDomicilio(this.idDomicilio)
      }

      let pedido = {
        "numeroPedido": 0,
        "horaEstimadaFin": new Date(),
        "total": this.total,
        "usuario": this.usuario,
        "tipoEnvio": this.tipoEnvio,
        "pedidoEstado": this.pedidoEstado,
        "domicilio": this.domicilio
      }

      console.log(pedido)

      // this.pedidoService.savePedido(pedido).subscribe(data => {
      //   console.log(data)
      // });

      //this.emptyCart();
      //this.refresh();
    } else {
      this.mercadoPagoService.redirectMercadoPago(this.total).subscribe(
        (data) => {
          window.location.href = data;
        },
        (err) => {
          console.log(err.error.text);
        }
      );
    }
  }
  /**
   * Captura el valor del radio button del tipo envio
   */
  capturarValorEnvio(event: any): void {
    this.idDomicilio = 0;
    this.idTipoEnvio = event.target.value;
    console.log(this.idTipoEnvio)
    this.listarDomicilios(this.usuario.id);
  }

  /** 
   * Captura el valor del radio button de la forma de pago
   */
  capturarValorFormaPago(event: any): void {
    this.idFormaPago = event.target.value;
  }

  /** 
  * Captura el valor del radio button de la forma de pago
  */
  capturarValorDomicilio(event: any): void {
    this.idDomicilio = event.target.value;
  }

  /**
   * Método void que a través del servicio de Tipo Envio, lista todos los tipos envios de la base de datos 
   */
  listarTiposEnvios(): void {
    this.tipoEnvioService.getTiposEnvios().subscribe((data) => {
      this.tiposEnvios = data;
    })
  }
  /**
   * Método void que a través del servicio de Domicilio, lista todos los domicilios de la base de datos
   */
  listarDomicilios(id:number): void {
    this.domicilioService.getDomicilioByUserId(id).subscribe((data) => {
      this.domicilios = data;
    })
  }

  restaurarTipoEnvio(): void {
    this.idDomicilio = 0;
    this.idTipoEnvio = 0;
  }

  checkTipoEnvio(): boolean {
    if (this.idTipoEnvio == 2 && this.idDomicilio > 0) {
      return false;
    } else if (this.idTipoEnvio == 1) {
      return false;
    } else {
      return true;
    }
  }

  getUserBuenSabor(email:string):void {
    this.authService.getDataUsuario(email).subscribe(dataUsuario => {
      this.usuario = dataUsuario;
      console.log(this.usuario)
    })
  }

  getUser(): void {
    let userName = this.tokenService.getUserName();
    this.authService.getDataUsuario(userName).subscribe(dataUsuario => {
      this.usuario = dataUsuario;
    });
  }

  getTipoEnvio(): void {
    this.tipoEnvioService.getTipoEnvioById(this.idTipoEnvio).subscribe(dataTipoEnvio => {
      this.tipoEnvio = dataTipoEnvio;
    });
  }

  getDomicilio(id: number): void {
    this.domicilioService.getDomicilioById(id).subscribe(dataDomicilio => {
      this.domicilio = dataDomicilio;
    })
  }

  getPedidoEstado(id:number): void {
    this.pedidoEstadoService.getPedidoEstadoById(id).subscribe(dataPedidoEstado => {
      this.pedidoEstado = dataPedidoEstado;
    })
  }

}
