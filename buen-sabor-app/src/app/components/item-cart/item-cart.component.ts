import { Component, Input, OnInit } from '@angular/core';
import { Articulo } from 'src/app/models/articulo';
import { DetallePedido } from 'src/app/models/detalle-pedido';
import { ArticuloService } from 'src/app/services/articulo.service';

@Component({
  selector: 'app-item-cart',
  templateUrl: './item-cart.component.html',
  styleUrls: ['./item-cart.component.css'],
})
export class ItemCartComponent implements OnInit {
  @Input() cartItem: DetallePedido;
  constructor() {}

  ngOnInit(): void {}
}
