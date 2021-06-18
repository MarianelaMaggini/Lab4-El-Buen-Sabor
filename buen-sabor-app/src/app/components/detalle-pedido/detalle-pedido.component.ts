import { Component, Input, OnInit } from '@angular/core';
import { Articulo } from 'src/app/models/articulo';
import { DetallePedido } from 'src/app/models/detalle-pedido';
import { ArticuloService } from 'src/app/services/articulo.service';

@Component({
  selector: 'app-detalle-pedido',
  templateUrl: './detalle-pedido.component.html',
  styleUrls: ['./detalle-pedido.component.css'],
})
export class DetallePedidoComponent implements OnInit {
  @Input() cartItem: DetallePedido;
  constructor() {}

  ngOnInit(): void {}
}
