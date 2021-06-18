import { Component, Input, OnInit } from '@angular/core';
import { Articulo } from 'src/app/models/articulo';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-item-articulo',
  templateUrl: './item-articulo.component.html',
  styleUrls: ['./item-articulo.component.css']
})
export class ItemArticuloComponent implements OnInit {

  @Input() articulo: Articulo;
  @Input() index: number;

  constructor(
    private messageService: MessageService) { }

  ngOnInit(): void { }
  
  addCart():void{
    this.messageService.sendMessage(this.articulo);
  }
}
