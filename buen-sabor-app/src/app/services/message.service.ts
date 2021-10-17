import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Articulo } from '../models/articulo';
import { RecetaElaborado } from '../models/receta-elaborado';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  
  message = new Subject();
  constructor() { }

  sendMessage(value: any):void {
      this.message.next(value);
  }

  getMessage(): Observable<any>{
    return this.message.asObservable();
  }
}
