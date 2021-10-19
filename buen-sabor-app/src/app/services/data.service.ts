import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  quantityZero$ = new EventEmitter<number>();
  quantityAdd$ = new EventEmitter<number>();
  quantityRemove$ = new EventEmitter<number>();
  active$ = new EventEmitter<boolean>();
  constructor() { }
}
