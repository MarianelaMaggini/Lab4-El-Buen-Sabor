import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { FacturaService } from 'src/app/services/factura.service';
import { DetalleFacturaService } from 'src/app/services/detalle-factura.service';

import { Factura } from 'src/app/models/factura';
import { DetalleFactura } from 'src/app/models/detalle-factura';

@Component({
  selector: 'app-listar-factura',
  templateUrl: './listar-factura.component.html',
  styleUrls: ['./listar-factura.component.css']
})
export class ListarFacturaComponent implements OnInit {

  titulo: string = 'Listado de facturas:';
  facturas: Factura[];
  detallesFactura: DetalleFactura[];
  factura: Factura;

  constructor(private facturaService: FacturaService, private detalleFacturaService: DetalleFacturaService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getAllFacturas();
  }

  getAllFacturas() {
    this.facturaService.getAllFacturas().subscribe(data =>{
      this.facturas = data;
      this.factura = data[0];
    });
  }

  verDetalle(id: number): void {
    this.detalleFacturaService.getDetalleByIdFactura(id).subscribe(data =>{
      this.detallesFactura = data;
      this.factura = data[0].factura;
    });
  }

  generarPDF() {
    
  }

}
