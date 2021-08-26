import { getLocaleDayPeriods } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Articulo } from 'src/app/models/articulo';
import { DetallePedido } from 'src/app/models/detalle-pedido';
import { Domicilio } from 'src/app/models/domicilio';
import { PedidoEstado } from 'src/app/models/pedido-estado';
import { PedidoCreate } from 'src/app/models/pedidoCreate';
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

const EMAIL_BUENSABOR = 'bsabor2021@gmail.com';
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
  usuario: Usuario;
  tipoEnvio: TipoEnvio;
  domicilio: Domicilio;
  pedidoEstado: PedidoEstado;
  paymentMethod: any;
  idShippingType: number;
  idPaymentMethod: number;
  idAddress: number;
  total: number;
  openForm: boolean;

  saveAddress = new FormGroup({
    id: new FormControl(''),
    calle: new FormControl(''),
    numero: new FormControl(''),
    localidad: new FormControl(''),
    fechaBaja: new FormControl(''),
    usuario: new FormControl('')
  })

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
  ) {
    this.paymentMethod = ['Efectivo', 'Mercado Pago'];
    this.idShippingType = 0;
    this.idAddress = 0;
    this.total = 0;
    this.openForm = false;
  }

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
  toPay(): void {
    if (this.idPaymentMethod == 0) {

      if (this.idShippingType == 1) {
        this.authService.getDataUsuario(EMAIL_BUENSABOR).subscribe((dataUsuario) => {
          this.tipoEnvioService.getTipoEnvioById(this.idShippingType).subscribe((dataTipoEnvio) => {
            this.pedidoEstadoService.getPedidoEstadoById(1).subscribe((dataPedidoEstado) => {
              this.domicilioService.getDomicilioByUserId(dataUsuario.id).subscribe((dataDomicilio) => {
                let pedido = new PedidoCreate(0, new Date(), this.total, dataUsuario, dataTipoEnvio, dataPedidoEstado, dataDomicilio[0])
                console.log(pedido)
                this.pedidoService.savePedido(pedido).subscribe(data => {
                  console.log(data)
                  Swal.fire({
                    title: 'Pedido realizado con éxito',
                    icon: 'success',
                    text: 'Gracias por confiar en el Buen Sabor 🍕',
                  }).then(res => {
                    this.emptyCart();
                    this.refresh();
                  });

                });
              });
            });
          });
        });

      } else {
        let userName = this.tokenService.getUserName();
        this.authService.getDataUsuario(userName).subscribe((dataUsuario) => {
          this.tipoEnvioService.getTipoEnvioById(this.idShippingType).subscribe((dataTipoEnvio) => {
            this.pedidoEstadoService.getPedidoEstadoById(1).subscribe((dataPedidoEstado) => {
              this.domicilioService.getDomicilioById(this.idAddress).subscribe((dataDomicilio) => {
                let pedido = new PedidoCreate(0, new Date(), this.total, dataUsuario, dataTipoEnvio, dataPedidoEstado, dataDomicilio)
                console.log(pedido)
                this.pedidoService.savePedido(pedido).subscribe(data => {
                  console.log(data)
                  Swal.fire({
                    title: 'Pedido realizado con éxito',
                    icon: 'success',
                    text: 'Gracias por confiar en el Buen Sabor 🍕',
                  }).then(res => {
                    this.emptyCart();
                    this.refresh();
                  });
                });
              });
            });
          });
        });
      }

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
  captureShippingValue(event: any): void {
    this.idShippingType = event.target.value;
    this.idAddress = 0;
    console.log(this.idShippingType)
    this.listAddress(this.usuario.id);
  }

  /** 
   * Captura el valor del radio button de la forma de pago
   */
  capturePaymentMethod(event: any): void {
    this.idPaymentMethod = event.target.value;
  }

  /** 
  * Captura el valor del radio button de la forma de pago
  */
  captureAddressValue(event: any): void {
    this.idAddress = event.target.value;
  }

  /**
   * Método void que a través del servicio de Tipo Envio, lista todos los tipos envios de la base de datos 
   */
  listShippingType(): void {
    this.tipoEnvioService.getTiposEnvios().subscribe((data) => {
      this.tiposEnvios = data;
    })
  }
  /**
   * Método void que a través del servicio de Domicilio, lista todos los domicilios de la base de datos
   */
  listAddress(id: number): void {
    this.domicilioService.getDomicilioByUserId(id).subscribe((data) => {
      this.domicilios = data;
    })
  }

  restoreShippingType(): void {
    this.idAddress = 0;
    this.idShippingType = 0;
  }

  checkShippingType(): boolean {
    if (this.idShippingType == 2 && this.idAddress > 0) {
      return false;
    } else if (this.idShippingType == 1) {
      return false;
    } else {
      return true;
    }
  }

  getUser(): void {
    let userName = this.tokenService.getUserName();
    this.authService.getDataUsuario(userName).subscribe((data) => {
      this.usuario = data;
    });
  }

  clickOpenForm(): void {
    this.openForm = true;
  }

  clickCloseForm(): void {
    this.openForm = false;
  }

  postFormAddress(form: Domicilio): void {
    let userName = this.tokenService.getUserName();
    this.authService.getDataUsuario(userName).subscribe((data) => {
      let domicilio = { "id": 0, "calle": form.calle, "numero": form.numero, "localidad": form.localidad, "fechaBaja": null, "usuario": data };
      this.domicilioService.saveDomicilio(domicilio).subscribe((data) => {
        console.log(data)
      })
      this.listAddress(data.id)
    });
  }

  deleteAddress(id: number): void {
    let userName = this.tokenService.getUserName();
    this.authService.getDataUsuario(userName).subscribe((data) => {
      this.domicilioService.getDomicilioById(id).subscribe((dataDomicilio) => {
        let domicilio = { "id": dataDomicilio.id, "calle": dataDomicilio.calle, "numero": dataDomicilio.numero, "localidad": dataDomicilio.localidad, "fechaBaja": new Date(), "usuario": data };
        this.domicilioService.saveDomicilio(domicilio).subscribe((data) => {
          console.log(data)
        })
        this.listAddress(data.id)
      })
    });
  }

}
