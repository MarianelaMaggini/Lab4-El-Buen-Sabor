import { Component, OnInit } from '@angular/core';

import { FacturaService } from 'src/app/services/factura.service';
import { DetallePedidoService } from 'src/app/services/detalle-pedido.service';

import { Factura } from 'src/app/models/factura';
import { DetallePedido } from 'src/app/models/detalle-pedido';

import { jsPDF } from "jspdf";
import html2canvas from 'html2canvas';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-listar-factura',
  templateUrl: './listar-factura.component.html',
  styleUrls: ['./listar-factura.component.css']
})
export class ListarFacturaComponent implements OnInit {

  titulo: string = 'Listado de facturas:';
  facturas: Factura[];
  factura: Factura;
  detallesPedido: DetallePedido[];
  montoDescuento: number;
  total: number;

  constructor(private facturaService: FacturaService, private detallePedidoService: DetallePedidoService) { }

  ngOnInit(): void {
    this.getAllFacturas();
  }

  getAllFacturas() {
    this.facturaService.getAllFacturas().subscribe(data =>{
      this.facturas = data;
      this.factura = data[0];
    });
  }

  getFactura(id: number): void {
    this.facturaService.getFacturaById(id).subscribe(data =>{
      this.factura =  data;
      this.detallePedidoService.getDetalleByIdPedido(this.factura.pedido.numeroPedido).subscribe(data =>{
        this.detallesPedido = data;
        this.calcularMontos();
      });
    });
  }

  calcularMontos() {
    this.montoDescuento = 0;
    this.total = 0;
    this.detallesPedido.forEach(item => {
      this.total += item.subtotal;
    });
    this.montoDescuento = this.total * this.factura.montoDescuento;
    this.total = this.total - this.montoDescuento;
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
