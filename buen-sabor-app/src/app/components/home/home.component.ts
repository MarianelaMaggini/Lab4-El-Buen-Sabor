import { Component, OnInit } from '@angular/core';
import { Articulo } from 'src/app/models/articulo';
import { ArticuloService } from 'src/app/services/articulo.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  articulos: Articulo[];
  constructor(private articuloService: ArticuloService) { }

  ngOnInit(): void {
    this.listar();
  }

  listar():void {
    this.articuloService.getArticulos().subscribe((data) => {
      console.log(data)
      this.articulos = data
    })
  }

}
