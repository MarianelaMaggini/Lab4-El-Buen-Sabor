import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { FacturaService } from 'src/app/services/factura.service';
import { DetalleFacturaService } from 'src/app/services/detalle-factura.service';

import { Factura } from 'src/app/models/factura';
import { DetalleFactura } from 'src/app/models/detalle-factura';

import { jsPDF } from "jspdf";
import html2canvas from 'html2canvas';

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
    const DATA = document.getElementById('factura');
    const doc = new jsPDF('p', 'pt', 'a4');
    const options = {
      background: 'white',
      scale: 2
    };
    html2canvas((DATA)!, options).then((canvas) => {

      const img = canvas.toDataURL('image/PNG');

      // Add image Canvas to PDF
      const bufferX = 15;
      const bufferY = 15;
      const imgProps = (doc as any).getImageProperties(img);
      const pdfWidth = doc.internal.pageSize.getWidth() - 2 * bufferX;
      const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;
      doc.addImage(img, 'PNG', bufferX, bufferY, pdfWidth, pdfHeight, undefined, 'FAST');
      return doc;
    }).then((docResult) => {
      docResult.save(`${this.factura.fecha}_factura.pdf`);
    });
  }

}
