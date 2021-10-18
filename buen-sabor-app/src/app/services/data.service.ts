import { Injectable, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  
  quantityAdd$ = new EventEmitter<number>();
  quantityRemove$ = new EventEmitter<number>();
  constructor() { }
}
