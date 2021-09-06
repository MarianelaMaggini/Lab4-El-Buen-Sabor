import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  state: boolean = false;

  constructor(private articuloService: ArticuloService, private router: Router) { }

  ngOnInit(): void {
    this.listArticulos();
    this.listRubrosByTipoArticulo();
  }
  listArticulos(): void {
      this.articuloService.getArticulosByTipoArticuloId(2).subscribe((elaborado) => {
        console.log(elaborado)
        this.articuloService.getArticulosByTipoArticuloId(3).subscribe((noElaborado) => {
          console.log(noElaborado)
          this.articulos = elaborado.concat(noElaborado);
        })
      })
  }
  listRubrosByTipoArticulo(): void {
    this.articuloService.getArticuloByElaboradoOrNoElaboradoGroupByRubro(2, 3).subscribe((data) => {
      this.articulosRubros = data;
    })
  }

  detail(id: number):void{
    this.router.navigate(['/articuloElaboradoDetalles', id])
  }
  
  valueRubro(event: any): void {
    this.state = event.target.checked;
    console.log(this.state)
    if (this.state) {
      this.idRubro = event.target.value;
      this.articuloService.getArticulosByRubroId(this.idRubro).subscribe((data) => {
        this.articulos = data;
      })
    }else{
      this.idRubro = 0;
      this.listArticulos();
    }
    
  }
}
