import { Component, OnInit } from '@angular/core';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  cantidad: number;
  cartItems: any = [];
  constructor(private storageService: StorageService) {}

  ngOnInit(): void {
    let cant = 0;
    if (this.storageService.existCart()) {
      this.storageService.getCart().forEach((item) => {
        cant += item.cantidad;
      })
    this.cantidad = cant;
    }
  }
}
