import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Articulo } from 'src/app/models/articulo';

@Component({
  selector: 'app-item-articulo',
  templateUrl: './item-articulo.component.html',
  styleUrls: ['./item-articulo.component.css']
})
export class ItemArticuloComponent implements OnInit {
  
  @Input() articulo: Articulo;
  @Input() index: number;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  cart(): void {
    this.router.navigate(['/cart'])
  }

}
