import { Injectable } from '@angular/core';
import { ItemCart } from '../models/item-cart';

@Injectable({
  providedIn: 'root',
})
export class StorageService {
  constructor() { }
  exist(key: string): boolean {
    return localStorage.getItem(key) != null;
  };

  set(key: string, data: any): void {
    try {
      localStorage.setItem(key, JSON.stringify(data));
    } catch (e) {
      console.log(e);
    }
  }

  get(key: string): any {
    try {
      return JSON.parse(localStorage.getItem(key)!);
    } catch (e) {
      console.log(e);
    }
  }

  clear(key: string): void {
    localStorage.removeItem(key);
  }
}
