import { Injectable } from '@angular/core';
import { ItemCart } from '../models/item-cart';

@Injectable({
  providedIn: 'root',
})
export class StorageService {
  constructor() {}
  existCart():boolean{
    return localStorage.getItem('cart') != null;
  };

  setCart(cart: ItemCart[]):void{
    localStorage.setItem('cart', JSON.stringify(cart));
  }

  getCart(): ItemCart[]{
    return JSON.parse(localStorage.getItem('cart')!);
  }

  clear():void{
    localStorage.removeItem('cart');
  }
}
