import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CantidadPedidos } from '../models/cantidadPedidos';
import { RankingComidas } from '../models/rankingComidas';
import { MontoGanancia } from '../models/montoGanancia';
import { Ingresos } from '../models/ingresos';

@Injectable({
  providedIn: 'root'
})
export class ReporteService {

  private reporteUrl: string = environment.reporteUrl;
  private header: HttpHeaders = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(private http: HttpClient) { }

  // Obtener cantidad de pedidos agrupados por cliente en un determinado periodo de tiempo
  getCantidadPedidos(fechaDesde: string, fechaHasta: string): Observable<CantidadPedidos[]> {
    return this.http.get<CantidadPedidos[]>(this.reporteUrl + "/cantidadPedidos?fechaDesde=" + fechaDesde + "&fechaHasta=" + fechaHasta , { headers: this.header });
  }

  // Obtener ranking comidas más pedidas en un periodo de tiempo determinado
  getRankingComidas(fechaDesde: string, fechaHasta: string): Observable<RankingComidas[]> {
    return this.http.get<RankingComidas[]>(this.reporteUrl + "/rankingComidas?fechaDesde=" + fechaDesde + "&fechaHasta=" + fechaHasta , { headers: this.header });
  }

  // Obtener monto de Ganancia en un periodo de tiempo (ventas menos costos)
  getMontoGanancia(fechaDesde: string, fechaHasta: string): Observable<MontoGanancia> {
    return this.http.get<MontoGanancia>(this.reporteUrl + "/montoGanancia?fechaDesde=" + fechaDesde + "&fechaHasta=" + fechaHasta , { headers: this.header });
  }

  // Obtener ingresos (recaudaciones) por períodos de tiempo. Mensual
  getIngresosMensuales(): Observable<Ingresos[]> {
    return this.http.get<Ingresos[]>(this.reporteUrl + "/ingresosMensuales" , { headers: this.header });
  }

  // Obtener ingresos (recaudaciones) por períodos de tiempo. Diario
  getIngresosDiarios(): Observable<Ingresos[]> {
    return this.http.get<Ingresos[]>(this.reporteUrl + "/ingresosDiarios" , { headers: this.header });
  }

}
