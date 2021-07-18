import { Component, OnInit } from '@angular/core';
import { MercadoPagoService } from 'src/app/services/mercado-pago.service';

@Component({
  selector: 'app-forma-pago',
  templateUrl: './forma-pago.component.html',
  styleUrls: ['./forma-pago.component.css']
})
export class FormaPagoComponent implements OnInit {
  total: number = 0;
  constructor(private mercadoPagoService: MercadoPagoService) { }

  ngOnInit(): void {
  }
  pagar(): void {
    this.mercadoPagoService.redirectMercadoPago(this.total).subscribe(
      (data) => {
        window.location.href = data;
      },
      (err) => {
        console.log(err.error.text);
      }
    );
  }
}
