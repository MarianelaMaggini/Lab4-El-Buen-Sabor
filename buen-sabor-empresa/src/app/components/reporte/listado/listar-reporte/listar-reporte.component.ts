import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ReporteService } from 'src/app/services/reporte.service';
import { FileService } from 'src/app/services/file.service';
import { TokenService } from 'src/app/services/token.service';

import { CantidadPedidos } from 'src/app/models/cantidadPedidos';
import { RankingComidas } from 'src/app/models/rankingComidas';
import { MontoGanancia } from 'src/app/models/montoGanancia';
import { Ingresos } from 'src/app/models/ingresos';

@Component({
  selector: 'app-listar-reporte',
  templateUrl: './listar-reporte.component.html',
  styleUrls: ['./listar-reporte.component.css']
})
export class ListarReporteComponent implements OnInit {

  titulo: string = 'Listado de reportes:';
  tipoReporte: number;
  reporteGenerado: number = 0;
  cantidadPedidos: CantidadPedidos[];
  rankingComidas: RankingComidas[];
  montoGanancia: MontoGanancia;
  ingresos: Ingresos[];
  fechaDesde: string;
  fechaHasta: string;
  isLogged = false;
  isAdmin = false;

  fechasForm = new FormGroup({
    fechaDesde: new FormControl('', Validators.required),
    fechaHasta: new FormControl('', Validators.required)
  });

  constructor(private reporteService: ReporteService, private fileService: FileService, private router: Router, private tokenService: TokenService) { }

  ngOnInit(): void {
    this.isAdmin = this.tokenService.isAdmin();
    this.isLogged = this.tokenService.isLogged();
  }

  capturarValor(event: any) {
    this.tipoReporte = event.target.value;
    this.reporteGenerado = 0;
    if (this.tipoReporte == 4) {
      this.reporteService.getIngresosMensuales().subscribe(data =>{
        this.ingresos = data;
      });
      this.reporteGenerado = 1;
    }
    if (this.tipoReporte == 5) {
      this.reporteService.getIngresosDiarios().subscribe(data =>{
        this.ingresos = data;
      });
      this.reporteGenerado = 1;
    }
  }

  async postForm(form: any) {
    this.fechaDesde = form.fechaDesde;
    this.fechaHasta = form.fechaHasta;
    if (this.tipoReporte == 1) {
      this.reporteService.getCantidadPedidos(form.fechaDesde, form.fechaHasta).subscribe(data =>{
        this.cantidadPedidos = data;
      });
    }
    if (this.tipoReporte == 2) {
      this.reporteService.getRankingComidas(form.fechaDesde, form.fechaHasta).subscribe(data =>{
        this.rankingComidas = data;
      });
    }
    if (this.tipoReporte == 3) {
      this.reporteService.getMontoGanancia(form.fechaDesde, form.fechaHasta).subscribe(data =>{
        this.montoGanancia = data;
      });
    }
    this.reporteGenerado = 1;
  }

  exportExcel() {
    if (this.tipoReporte == 1) {
      this.reporteService.getCantidadPedidos(this.fechaDesde, this.fechaHasta).subscribe(data =>{
        this.fileService.exportAsExcelFile(data, 'Cantidad_pedidos_reporte_' + this.fechaDesde + '_' + this.fechaHasta);
      });
    }
    if (this.tipoReporte == 2) {
      this.reporteService.getRankingComidas(this.fechaDesde, this.fechaHasta).subscribe(data =>{
        this.fileService.exportAsExcelFile(data, 'Ranking_comidas_reporte_' + this.fechaDesde + '_' + this.fechaHasta);
      });
    }
    if (this.tipoReporte == 3) {
      this.reporteService.getMontoGanancia(this.fechaDesde, this.fechaHasta).subscribe(data =>{
        let montoGanancia: MontoGanancia[] = [];
        montoGanancia.push(data);
        this.fileService.exportAsExcelFile(montoGanancia, 'Monto_ganancia_reporte_' + this.fechaDesde + '_' + this.fechaHasta);
      });
    }
    if (this.tipoReporte == 4) {
      this.reporteService.getIngresosMensuales().subscribe(data =>{
        this.fileService.exportAsExcelFile(data, 'Ingresos_mensuales_reporte');
      });
    }
    if (this.tipoReporte == 5) {
      this.reporteService.getIngresosDiarios().subscribe(data =>{
        this.fileService.exportAsExcelFile(data, 'Ingresos_diarios_reporte');
      });
    }
  }

}
