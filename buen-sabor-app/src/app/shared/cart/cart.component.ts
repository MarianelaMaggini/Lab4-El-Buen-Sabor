import { Component, OnInit } from '@angular/core';
import { Articulo } from 'src/app/models/articulo';
import { ItemCart } from 'src/app/models/item-cart';
import { MessageService } from 'src/app/services/message.service';
import { StorageService } from 'src/app/services/storage.service';
import { Router } from '@angular/router';
import { ArticuloElaboradoDetalleService } from 'src/app/services/articulo-elaborado-detalle.service';
import { RecetaElaboradoService } from 'src/app/services/receta-elaborado.service';
import { concatMap } from 'rxjs/operators';
import { ArticuloElaboradoDetalle } from 'src/app/models/articulo-elaborado-detalle';
import { RecetaElaborado } from 'src/app/models/receta-elaborado';
import { ToastrService } from 'ngx-toastr';

const OPCION_UNO = 1;
const OPCION_DOS = 2;
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {

  /** atributos */
  cartItems: any = [];
  total: number;
  articuloDetalle: ArticuloElaboradoDetalle;
  recetasElaborados: RecetaElaborado[];
  available: boolean;

  /** @constructor */
  constructor(
    private messageService: MessageService,
    private router: Router,
    private storageService: StorageService,
    private articuloDetalleService: ArticuloElaboradoDetalleService,
    private recetaService: RecetaElaboradoService,
    private toastr: ToastrService
  ) {
    this.total = 0;
    this.available = true;
  }

  ngOnInit(): void {
    if (this.storageService.exist('cart')) {
      this.cartItems = this.storageService.get('cart');
    }
    this.getItem();
    this.total = this.getTotal();
  }

  /**
   * @description Método void para recargar la página
   */
  refresh(): void {
    this.router.navigate(['/'])
    location.reload()
  }

  /**
   * @description Método void que obtiene el articulo y lo añade al carrito
   */
  getItem(): void {
    this.messageService.getMessage().subscribe((articulo: Articulo) => {
      this.stockControl(articulo.id, OPCION_UNO);
      let exist = false;
      if (this.available) {
        this.cartItems.forEach((item: { id: number; cantidad: number }) => {
          if (item.id === articulo.id) {
            exist = true;
            item.cantidad++;
          }
        });
        if (!exist) {
          const cartItem = new ItemCart(articulo);
          this.cartItems.push(cartItem);
        }
        this.total = this.getTotal();
        this.storageService.set('cart',this.cartItems);
      }
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
   * @description Método void que limpia el carrito completamente
   */
  emptyCart(): void {
    this.cartItems = [];
    this.total = 0;
    this.storageService.clear('cart');
  }

  /**
   * Elimina el item reduciendo la cantidad
   * @param i Recibe por parametro el indice del item 
   */
  deleteItem(i: number): void {
    console.log(this.cartItems[i].id)
    this.stockControl(this.cartItems[i].id, OPCION_DOS)
    if (this.cartItems[i].cantidad > 1) {
      this.cartItems[i].cantidad--;
    } else {
      this.cartItems.splice(i, 1);
    }
    this.total = this.getTotal();
    this.storageService.set('cart', this.cartItems);
  }

  stockControl(id: number, opcion: number): void {
    let inventarios = this.storageService.get('inventario');
    this.articuloDetalleService.getArtElaboradoDetalleByArticuloId(id).pipe(
      concatMap(data => {
        this.articuloDetalle = data;
        return this.recetaService.getRecetaByArticuloDetalleId(data.id);
      }),
    ).subscribe(data => {
      this.incrementOrDecrementInventory(data, inventarios, opcion);
    });
  }

  incrementOrDecrementInventory(recetas: any, inventarios:any, opcion:number):void{
    recetas.forEach((r: { articulo: { id: number; }; cantidad: number; }) => {
      inventarios.forEach((i: { articulo: { id: number; }; stockActual: number; stockMinimo: number; }) => {
          if (i.articulo.id === r.articulo.id) {
            if (opcion == 1) {
              i.stockActual -= r.cantidad;
              if (i.stockMinimo < i.stockActual && i.stockMinimo > r.cantidad) {
                this.available = true;
              } else {
                this.available = false;
                this.toastr.error('Se ha agotado el stock para este artículo', 'Disculpe');
              }
            }
            if (opcion == 2) {
              i.stockActual += r.cantidad;
            }
          }
        });
        this.storageService.set('inventario', inventarios);
      });
      
  }
}

