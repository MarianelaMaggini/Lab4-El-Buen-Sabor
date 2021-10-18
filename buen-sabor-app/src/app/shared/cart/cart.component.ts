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
import { DataService } from 'src/app/services/data.service';

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
  recetasElaborados: RecetaElaborado[];
  articuloDetalle: ArticuloElaboradoDetalle;
  total: number;
  available: boolean;
  quantity: number;

  /** @constructor */
  constructor(
    private messageService: MessageService,
    private router: Router,
    private storageService: StorageService,
    private articuloDetalleService: ArticuloElaboradoDetalleService,
    private recetaService: RecetaElaboradoService,
    private toastr: ToastrService,
    private dataService: DataService
  ) {
    this.total = 0;
    this.available = true;
    this.quantity = 0;
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
      let inventarios = this.storageService.get('inventario');
      if (articulo.tipoArticulo.id == 2) {
        this.stockControl(OPCION_UNO, articulo, inventarios);
      }
      if(articulo.tipoArticulo.id == 3){
        inventarios.forEach((inventarioTemp: { articulo: { id: number; }; stockActual: number; stockMinimo: number; }) => {
          if (inventarioTemp.articulo.id === articulo.id) {
            this.available = true;
            inventarioTemp.stockActual--;
            if (inventarioTemp.stockMinimo > inventarioTemp.stockActual) {
              inventarioTemp.stockActual++;
              this.available = false;
              this.dataService.quantityRemove$.emit(1);
              this.toastr.error('Se ha agostado el artículo ' + articulo.denominacion, 'Disculpe');            
            }
              this.addCart(articulo);
            }
        });
        this.storageService.set('inventario', inventarios);
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
    this.dataService.quantityRemove$.emit(1);
    let inventarios = this.storageService.get('inventario');
    if (this.cartItems[i]) {
      this.dataService.active$.emit(true);
    }
    if (this.cartItems[i].tipoArticulo.id == 2) {
      this.stockControl(OPCION_DOS, this.cartItems[i], inventarios, i)
    }
    if(this.cartItems[i].tipoArticulo.id == 3){
      inventarios.forEach((inventarioTemp: { articulo: { id: number; }; stockActual: number; }) => {
        if (this.cartItems[i] != null) {
          if (inventarioTemp.articulo.id === this.cartItems[i].id) {
            this.available = true;
            inventarioTemp.stockActual++;
            this.removeCart(i);
          }
        }
      });
      this.storageService.set('inventario', inventarios);
    }
    
  }

  stockControl(opcion: number, articulo: Articulo, inventarios:any, i?: number): void {
    this.articuloDetalleService.getArtElaboradoDetalleByArticuloId(articulo.id).pipe(
      concatMap(data => {
        this.articuloDetalle = data;
        return this.recetaService.getRecetaByArticuloDetalleId(data.id);
      }),
    ).subscribe(data => {
      this.incrementOrDecrementInventory(data, inventarios, opcion, articulo, i);
    });
  }

  incrementOrDecrementInventory(recetas: any, inventarios:any, opcion:number, articulo: Articulo, i?: number):void{
    recetas.forEach((r: { articulo: { id: number; }; cantidad: number; }) => {
      inventarios.forEach((invetarioTemp: { articulo: { id: number; }; stockActual: number; stockMinimo: number; }) => {
          if (invetarioTemp.articulo.id === r.articulo.id) {
            if (opcion == 1) {
              invetarioTemp.stockActual -= r.cantidad;
              if (invetarioTemp.stockMinimo < invetarioTemp.stockActual && invetarioTemp.stockMinimo > r.cantidad) {
                this.available = true;
                this.addCart(articulo);
              } else {
                invetarioTemp.stockActual += r.cantidad
                this.available = false;
                this.dataService.quantityRemove$.emit(1);
                this.toastr.error('Se ha agostado el artículo ' + articulo.denominacion, 'Disculpe');
              }
            }
            if (opcion == 2) {
              invetarioTemp.stockActual += r.cantidad;
              this.removeCart(i!)
            }
          }
          
        });
        this.storageService.set('inventario', inventarios);
        
      });   
  }

  removeCart(i: number):void{
    if (this.cartItems[i].cantidad > 1) {
      this.cartItems[i].cantidad--;
    } else {
      this.cartItems.splice(i, 1);
    }
    this.total = this.getTotal();
    this.storageService.set('cart', this.cartItems);
  }

  addCart(articulo: any):void{
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
  }

}

