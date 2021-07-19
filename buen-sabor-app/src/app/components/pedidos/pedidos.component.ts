import {  Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pedidos',
  templateUrl: './pedidos.component.html',
  styleUrls: ['./pedidos.component.css']
})
export class PedidosComponent implements OnInit {
  total: number = 750;
  fecha: Date = new Date();
  constructor() { }

  ngOnInit(): void {
  }

}
