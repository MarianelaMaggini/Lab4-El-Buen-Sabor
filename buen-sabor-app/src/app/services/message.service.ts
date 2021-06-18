import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Articulo } from '../models/articulo';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  
  message = new Subject();
  constructor() { }

  sendMessage(articulo: Articulo):void {
      this.message.next(articulo);
  }

  getMessage(): Observable<any>{
    return this.message.asObservable();
  }
}
