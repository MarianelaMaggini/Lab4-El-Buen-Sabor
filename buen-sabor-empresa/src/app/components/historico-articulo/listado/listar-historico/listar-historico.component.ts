import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { HistoricoArticuloService } from 'src/app/services/historico-articulo.service';
import { ArticuloService } from 'src/app/services/articulo.service';

import { HistoricoArticulo } from 'src/app/models/historico-articulo';
import { Articulo } from 'src/app/models/articulo';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-listar-historico',
  templateUrl: './listar-historico.component.html',
  styleUrls: ['./listar-historico.component.css']
})
export class ListarHistoricoComponent implements OnInit {

  titulo: string = 'Histórico de compras:';
  historicoArticulo: HistoricoArticulo[];
  articulos: Articulo[];
  idArticulo: number;
  isLogged = false;
  isAdmin = false;
  constructor(
    private historicoService: HistoricoArticuloService, 
    private articuloService: ArticuloService, 
    private router: Router, private activatedRoute: ActivatedRoute,
    private tokenService: TokenService
    ) { }

  ngOnInit(): void {
    this.isAdmin = this.tokenService.isAdmin();
    this.isLogged = this.tokenService.isLogged();
    this.getArticulosHistorico();
  }

  getArticulosHistorico() {
    this.articuloService.getArticulosByTipo(1).subscribe(insumos =>{
      this.articuloService.getArticulosByTipo(3).subscribe(noElaboradosPorNosotros =>{
        this.articulos = insumos.concat(noElaboradosPorNosotros);
      });
    });
  }

  capturarValor(event: any) {
    this.idArticulo = event.target.value;
    if(this.idArticulo != 0 && this.idArticulo != null) {
      this.historicoService.getHistoricoArticuloByIdArticulo(this.idArticulo).subscribe(data =>{
        this.historicoArticulo = data;
      });
    }
  }

}
