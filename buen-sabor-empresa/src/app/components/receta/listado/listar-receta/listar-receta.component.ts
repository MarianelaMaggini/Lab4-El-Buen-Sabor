import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { RecetaService } from 'src/app/services/receta.service';
import { ArticuloService } from 'src/app/services/articulo.service';
import { AedService } from 'src/app/services/aed.service';

import { Receta } from 'src/app/models/receta';
import { Articulo } from 'src/app/models/articulo';

@Component({
  selector: 'app-listar-receta',
  templateUrl: './listar-receta.component.html',
  styleUrls: ['./listar-receta.component.css']
})
export class ListarRecetaComponent implements OnInit {

  titulo: string = 'Listado de recetas:';
  receta: Receta[];
  articulos: Articulo[];
  idArticulo: number;

  constructor(private recetaService: RecetaService, private articuloService: ArticuloService, private aedService: AedService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getArticulosElaboradosPropios();
  }

  getArticulosElaboradosPropios() {
    this.articuloService.getArticulosByTipo(2).subscribe(data =>{
      this.articulos = data;
    });
  }

  capturarValor(event: any) {
    this.idArticulo = event.target.value;
    if(this.idArticulo != 0 && this.idArticulo != null) {
      this.aedService.getAedByIdArticulo(this.idArticulo).subscribe(aed =>{
        this.recetaService.getRecetaByIdArticulo(aed.id).subscribe(data =>{
          this.receta = data;
        });
      });
    }
  }

  editarItem(item: number, idArticulo: number): void {
    this.router.navigate(['nueva-receta', item, idArticulo]);
  }

}
