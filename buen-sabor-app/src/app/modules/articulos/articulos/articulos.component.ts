import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { forkJoin } from 'rxjs';
import { Articulo } from 'src/app/models/articulo';
import { ArticuloService } from 'src/app/services/articulo.service';

@Component({
  selector: 'app-articulos',
  templateUrl: './articulos.component.html',
  styleUrls: ['./articulos.component.css']
})
export class ArticulosComponent implements OnInit {
  articulos: Articulo[];
  articulosRubros: Articulo[];
  idRubro: number = 0;
  denominacion: string = "";
  state: boolean;
  checked: boolean;
  filters: Map<number, string> = new Map<number, string>();
  constructor(private articuloService: ArticuloService, private router: Router) { }

  ngOnInit(): void {
    this.listArticulos();
    this.listRubrosByTipoArticulo();
  }

  /**
   * @description forkJoin me permite hacer peticiones en paralelo, luego que obtenga toda la info, 
   * concateno los array para asignarlo al array de articulos
   */
  listArticulos(): void {
    forkJoin(
      [this.articuloService.getArticulosByTipoArticuloId(2), 
      this.articuloService.getArticulosByTipoArticuloId(3)])
      .subscribe(data => {
        this.articulos = data[0].concat(data[1])
    })
  }

  listRubrosByTipoArticulo(): void {
    this.articuloService.getArticuloByElaboradoOrNoElaboradoGroupByRubro(2, 3).subscribe((data) => {
      this.articulosRubros = data;
    })
  }

  detail(id: number): void {
    this.router.navigate(['/home/detalle', id])
  }

  valueRubro(event: any): void {
    this.state = event.target.checked;
    if (this.state) {
      this.idRubro = event.target.value;
      this.denominacion = event.target.name
      this.filters.set(this.idRubro, this.denominacion);

    } else {
      this.idRubro = 0;
      this.listArticulos();
    }

  }

  filterByRubro(filters: Map<number, string>) {
    if (this.state) {
      this.articulos = [];
        filters.forEach((value, key) => {
        this.articuloService.getArticulosByRubroId(key).subscribe((data) => {
          data.forEach((articulo) => {
              this.articulos.push(articulo)
          })
        })
      })
    }else{
      this.listArticulos();
    }

  }

  clearFilters():void{
    this.filters.clear();
    this.checked = false;
    location.reload();
  }
}