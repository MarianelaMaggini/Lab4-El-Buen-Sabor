import { Component, OnInit } from '@angular/core';
import { Articulo } from 'src/app/models/articulo';
import { DetallePedido } from 'src/app/models/detalle-pedido';
import { Domicilio } from 'src/app/models/domicilio';
import { TipoEnvio } from 'src/app/models/tipo-envio';
import { DomicilioService } from 'src/app/services/domicilio.service';
import { MercadoPagoService } from 'src/app/services/mercado-pago.service';
import { MessageService } from 'src/app/services/message.service';
import { StorageService } from 'src/app/services/storage.service';
import { TipoEnvioService } from 'src/app/services/tipo-envio.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {

  // atributos
  tiposEnvios: TipoEnvio[];
  idTipoEnvio: number;
  domicilios: Domicilio[];
  cartItems: any = [];
  total: number = 0;
  formasPago: any = ['Efectivo', 'Mercado Pago'];
  idFormaPago : number;
  
  // constructor
  constructor(
    private messageService: MessageService,
    private storageService: StorageService,
    private mercadoPagoService: MercadoPagoService,
    private tipoEnvioService: TipoEnvioService,
    private domicilioService: DomicilioService
  ) {}
  
  ngOnInit(): void {
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
      this.emptyCart();
      this.refresh();
    }else {
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
  capturarValorEnvio(event:any):void {
    this.idTipoEnvio = event.target.value;
    console.log(this.idTipoEnvio);
    this.listarDomicilios();
  }

    /** 
     * Captura el valor del radio button de la forma de pago
     */ 
    capturarValorFormaPago(event:any):void {
      this.idFormaPago = event.target.value;
      console.log(this.idFormaPago)
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
  listarDomicilios():void {
    this.domicilioService.getTiposEnvios().subscribe((data) => {
      this.domicilios = data;
    })
  }
}
