import { Injectable } from '@angular/core';
import { DetallePedido } from '../models/detalle-pedido';

@Injectable({
  providedIn: 'root',
})
export class StorageService {
  constructor() {}
  existCart():boolean{
    return localStorage.getItem('cart') != null;
  };

  setCart(cart: DetallePedido[]):void{
    localStorage.setItem('cart', JSON.stringify(cart));
  }

  getCart(): DetallePedido[]{
    return JSON.parse(localStorage.getItem('cart')!);
  }

  clear():void{
    localStorage.removeItem('cart');
  }
}
