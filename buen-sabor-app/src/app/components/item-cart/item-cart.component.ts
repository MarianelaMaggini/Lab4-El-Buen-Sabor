import { Component, Input, OnInit } from '@angular/core';
import { ItemCart } from 'src/app/models/item-cart';

@Component({
  selector: 'app-item-cart',
  templateUrl: './item-cart.component.html',
  styleUrls: ['./item-cart.component.css'],
})
export class ItemCartComponent implements OnInit {
  @Input() cartItem: ItemCart;
  constructor() {}

  ngOnInit(): void {}
}
