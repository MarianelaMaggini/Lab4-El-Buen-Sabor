import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { concatMap } from 'rxjs/operators';
import { Domicilio } from 'src/app/models/domicilio';
import { DomicilioForm } from 'src/app/models/domicilio-form';
import { Localidad } from 'src/app/models/localidad';
import { TipoEnvio } from 'src/app/models/tipo-envio';
import { Usuario } from 'src/app/models/usuario';
import { ArticuloService } from 'src/app/services/articulo.service';
import { AuthService } from 'src/app/services/auth.service';
import { DetallePedidoService } from 'src/app/services/detalle-pedido.service';
import { DomicilioService } from 'src/app/services/domicilio.service';
import { LocalidadService } from 'src/app/services/localidad.service';
import { MercadoPagoService } from 'src/app/services/mercado-pago.service';
import { PedidoService } from 'src/app/services/pedido.service';
import { PedidoEstadoService } from 'src/app/services/pedidoEstado.service';
import { StorageService } from 'src/app/services/storage.service';
import { TipoEnvioService } from 'src/app/services/tipo-envio.service';
import { TokenService } from 'src/app/services/token.service';
import Swal from 'sweetalert2';
import * as SockJS from 'sockjs-client';
import * as Stomp from '@stomp/stompjs';
import { PedidoCreate } from 'src/app/models/pedidoCreate';
import { PedidoEstado } from 'src/app/models/pedido-estado';
import { Pedido } from 'src/app/models/pedido';
import { Router } from '@angular/router';
import { InventarioService } from 'src/app/services/inventario.service';

const EMAIL_BUENSABOR = 'bsabor2021@gmail.com';
@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit, OnDestroy {

  /** Atributos */
  cartItems: any = [];
  tiposEnvios: TipoEnvio[];
  domicilios: Domicilio[];
  localidades: Localidad[];
  usuario: Usuario;
  usuarioBuenSabor: Usuario;
  domicilio: Domicilio;
  localidad: Localidad;
  paymentMethod: any;
  idShippingType: number;
  idPaymentMethod: number;
  valuePaymentMethod: string;
  idAddress: number;
  total: number;
  openForm: boolean;
  emailUser: string;
  webSocketEndPoint: string;
  disabled: boolean;
  buttonPay: boolean;
  stompClient: any;
  message: string;
  formAddress: FormGroup;

  /** @constructor */
  constructor(
    private storageService: StorageService,
    private mercadoPagoService: MercadoPagoService,
    private tipoEnvioService: TipoEnvioService,
    private domicilioService: DomicilioService,
    private pedidoEstadoService: PedidoEstadoService,
    private pedidoService: PedidoService,
    private tokenService: TokenService,
    private authService: AuthService,
    private articuloService: ArticuloService,
    private detallePedidoService: DetallePedidoService,
    private localidadService: LocalidadService,
    private router: Router,
    private inventarioService: InventarioService,

  ) {
    this.paymentMethod = ['Efectivo', 'Mercado Pago'];
    this.idShippingType = 0;
    this.idAddress = 0;
    this.total = 0;
    this.openForm = false;
    this.webSocketEndPoint = 'http://localhost:8080/ws';
    this.message = 'Ingresó un nuevo pedido.';
    this.disabled = true;
    this.buttonPay = false;

    /** Formulario para domicilio */
    this.formAddress = new FormGroup({
      id: new FormControl(''),
      calle: new FormControl('', [Validators.required]),
      numero: new FormControl('', [Validators.required, Validators.maxLength(4)]),
      idLocalidad: new FormControl('', [Validators.required]),
      fechaBaja: new FormControl(''),
      usuario: new FormControl('')
    })
  }
 

  ngOnInit(): void {
    this.emailUser = this.tokenService.getUserName()
    this.getUser(this.emailUser);
    this.getUserBuenSabor(EMAIL_BUENSABOR);
    this.getItems();
    this.getTotal();
    this.listShippingType();
    this.connect();
  }

  ngOnDestroy(): void {
    this.disconnect();
  }

  /**
   * @description Recupera la información completa del usuario mediante el correo electrónico
   * @param email 
   */
   getUser(email: string): void {
    this.authService.getDataUsuario(email).subscribe((data) => {
      this.usuario = data;
    });
  }

   /**
   * @description Recupera la información completa del usuario mediante el correo electrónico
   * @param email 
   */
    getUserBuenSabor(email: string): void {
      this.authService.getDataUsuario(email).subscribe((data) => {
        this.usuarioBuenSabor = data;
      });
    }

  /**
   * @description Método void que obtiene el articulo y lo añade a la tabla
   */
  getItems(): void {
    this.cartItems = this.storageService.get('cart');
  }

   /**
   * Este método itera sobre los items del carrito y suma al total el precio por la cantidad de cada item
   * @returns el precio total del carrito
   */
    getTotal(): number {
      this.cartItems.forEach((item: { cantidad: number; precio: number }) => {
        this.total += item.cantidad * item.precio;
      });
      return +this.total.toFixed(2);
    }

  /**
  * @description Método void que a través del servicio de Tipo Envio, 
  * lista todos los tipos envios de la base de datos 
  */
  listShippingType(): void {
    this.tipoEnvioService.getTiposEnvios().subscribe((data) => {
      this.tiposEnvios = data;
    })
  }
  /**
   * @description Captura el valor del radio button del tipo envio
   */
  captureShippingValue(event: any): void {
    this.idShippingType = event.target.value;
    this.idAddress = 0;
    if (this.idShippingType == 1) {
      this.listAddress(this.usuarioBuenSabor.id);
    }
    if(this.idShippingType == 2){
      this.listAddress(this.usuario.id);
    }
  }

  /**
   * @description Evalua si el retiro envio fue seleccionado
   * En caso de envio a domicilio, debe elegir un domicilio sino el botón sigue inhabilido
   * @returns valor true o false según corresponda
   */
   checkShippingType(): boolean {
    if (this.idShippingType == 2 && this.idAddress > 0) {
      return true;
    } else if (this.idShippingType == 1 && this.idAddress > 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * @description Listado de localidades
   */
   listLocalities(): void {
    this.localidadService.getLocalidades().subscribe((localidad) => {
      this.localidades = localidad;
    })
  }

  /**
   * @description Método void que a través del servicio de Domicilio, 
   * lista todos los domicilios de la base de datos
   */
  listAddress(id: number): void {
    this.domicilioService.getDomicilioByUserId(id).subscribe((data) => {
      this.domicilios = data;
    })
  }

  /** 
   * @description Captura el valor del radio button del domicilio
   */
  captureAddressValue(event: any): void {
    this.idAddress = event.target.value;
  }

  /**
   * @description Abre el formulario para cargar un domicilio
   */
   clickOpenForm(): void {
    this.openForm = true;
    this.listLocalities();
  }

  /**
   * @description Cierra el formulario para cargar un domicilio
   */
  clickCloseForm(): void {
    this.openForm = false;
  }

  /**
   * @param form 
   * @description Mediante petición post envía un domicilio al servidor para persistirlo
   */
   saveNewAddress(form: DomicilioForm): void {
    this.localidadService.getLocalidadById(form.idLocalidad).pipe(
      concatMap(dataLocalidad => {
        let domicilio = { "id": 0, "calle": form.calle, "numero": form.numero, "localidad": dataLocalidad, "fechaBaja": null, "usuario": this.usuario };
        this.domicilios.push(domicilio);
        return this.domicilioService.saveDomicilio(domicilio)
      }),
    ).subscribe();
    this.formAddress.reset();
  }

  /**
   * @param form
   * @description Mediante petición post envía un domicilio existente al servidor para persistirlo
   * lo elimina de manera lógica por fecha
   */
   deleteAddress(id: number): void {
    this.domicilios = this.domicilios.filter((item) => item.id !== id);
    this.domicilioService.getDomicilioById(id).pipe(
      concatMap(dataDomicilio => {
        let domicilio = { "id": dataDomicilio.id, "calle": dataDomicilio.calle, "numero": dataDomicilio.numero, "localidad": dataDomicilio.localidad, "fechaBaja": new Date(), "usuario": this.usuario };
        return this.domicilioService.saveDomicilio(domicilio);
      }),
    ).subscribe();
  }

  /** 
   * @description Captura el valor del radio button de la forma de pago
   */
   capturePaymentMethod(event: any): void {
    this.idPaymentMethod = event.target.value;
    let montoDescuento = 0.1
    if (this.idPaymentMethod == 0) {
      this.valuePaymentMethod = this.paymentMethod[0]
      montoDescuento *= this.total;
      this.total -= montoDescuento;
    }else{
      this.valuePaymentMethod = this.paymentMethod[1];
      this.total = 0;
      this.total = this.getTotal();
    }
    this.buttonPay = true;
    
  }

    /**
   * @description Iniciar conexión con el websocket
   */
     connect() {
      const socket = new SockJS(this.webSocketEndPoint);
      this.stompClient = Stomp.over(socket);
      const _this = this;
      _this.stompClient.connect({}, (frame: any) => {
        console.log('Connected: ' + frame)
      })
    }
  
    /**
     * @description Desconectar el websocket
     */
    disconnect() {
      if (this.stompClient != null) {
        this.stompClient.disconnect();
      }
      console.log('Disconnected!');
    }

  /**
   * @description Método void que finaliza la compra dependiendo de la forma de pago
   * Si tipo envio es 1 (retiro) Y metodo de pago es 0 (efectivo)
   * Si no Si tipo envio es 1 (retiro) Y metodo de pago es 1 (Mercado Pago)
   * Si No tipo envio es 2 (domicilio) Y metodo de pago es 1(Mercado Pago)
   */
  toPay(): void {
    if (this.idShippingType == 1 && this.idPaymentMethod == 0) {
      this.Payment();
    } else {
      if (this.idShippingType == 1 && this.idPaymentMethod == 1) {
        this.Payment();
      } else if (this.idShippingType == 2 && this.idPaymentMethod == 1) {
        this.Payment();
      }
    }
  }

  /**
   * @description Se procede a construir el pedido dependiendo de las selecciones y persistirlo en la bd
   */
  Payment():void{
    let domicilio: Domicilio;
    let pedidoEstado: PedidoEstado;
    let pedido: Pedido;
    let tipoEnvio: TipoEnvio;
    this.tipoEnvioService.getTipoEnvioById(this.idShippingType).pipe(
      concatMap((data) => {
        tipoEnvio = data;
        return this.pedidoEstadoService.getPedidoEstadoById(1)
      }),
      concatMap(data1 => {
        pedidoEstado = data1;
        return this.domicilioService.getDomicilioById(this.idAddress)
      }),
      concatMap(data2 => {
        domicilio = data2;
        pedido = new PedidoCreate(0, new Date(), this.total, this.usuario, tipoEnvio, pedidoEstado, domicilio, this.valuePaymentMethod)
        return this.pedidoService.savePedido(pedido)
      }),
    ).subscribe(data => {
      this.savePedidoAndDetallePedido(data);
    });
  }

  /**
   * @description Guarda un pedido y de acuerdo a cada item cargado en el carrito, 
   * los persiste luego de persistir el pedido
   * @param pedido 
   */
  savePedidoAndDetallePedido(pedido: PedidoCreate): void {
    this.stompClient.send('/app/pedidos', {}, JSON.stringify(pedido));
    this.cartItems.forEach((item: any) => {
      this.articuloService.getArticuloById(item.id).pipe(
        concatMap(dataArticulo => {
          let detallePedido = { "id": 0, "cantidad": item.cantidad, "subtotal": item.precio, "articulo": dataArticulo, "pedido": pedido }
          return this.detallePedidoService.saveDetallePedido(detallePedido);
        })
      ).subscribe(data => {
        Swal.fire({
          title: 'Pedido realizado con éxito',
          icon: 'success',
          text: 'Gracias por confiar en el Buen Sabor 🍕',
        }).then(res => {
          this.stompClient.send('/app/mensajes', {}, JSON.stringify({ 'message': this.message }));
          this.buttonPay = false;
          this.payWithMercadoPago();
          this.persistRealInventory();
        });
      });
    });
  }
  /**
   * @description Si el método del pago es mercado pago redirige al proceso de pago
   * de Mercado Pago
   */
  payWithMercadoPago(): void {
    if (this.idPaymentMethod == 1) {
      this.mercadoPagoService.redirectMercadoPago(this.total).subscribe(
        (data) => {
          this.emptyCart();
          window.location.href = data;
        },
        (err) => {
          console.log(err.error.text);
        }
      );
    }else if(this.idPaymentMethod == 0){
      this.emptyCart();
      this.router.navigate(['/']);
    }
  }

  /**
   * @description Método void que limpia el carrito completamente
   */
   emptyCart(): void {
    this.cartItems = [];
    this.total = 0;
    this.storageService.clear('cart');
  }

  /**
   * @description
   */
  persistRealInventory():void{
    let inventarioTemp = this.storageService.get('inventario');
     this.inventarioService.getInventarios().subscribe((data) => {
       data.forEach(itemReal => {
        let itemTemp = inventarioTemp.filter((x: { id: number; }) => { return x.id == itemReal.id })
        if (itemTemp[0].stockActual < itemReal.stockActual) {
          this.inventarioService.updateInventario(itemTemp[0]).subscribe();
        }
      })
     })  
  }
}