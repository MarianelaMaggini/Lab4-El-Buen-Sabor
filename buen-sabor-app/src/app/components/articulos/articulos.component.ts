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
  constructor(private articuloService: ArticuloService, private router: Router) { }

  ngOnInit(): void {
    this.listar();
  }
  listar():void {
    this.articuloService.getArticulosByTipoArticuloId(2).subscribe((elaborado) => {
      console.log(elaborado)
      this.articuloService.getArticulosByTipoArticuloId(3).subscribe((noElaborado) => {
        console.log(noElaborado)
        this.articulos = elaborado.concat(noElaborado);
      })
    })
  }
}
