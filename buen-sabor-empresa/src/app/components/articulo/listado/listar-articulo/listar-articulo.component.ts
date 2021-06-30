import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { ArticuloService } from 'src/app/services/articulo.service';

import { Articulo } from 'src/app/models/articulo';

@Component({
  selector: 'app-listar-articulo',
  templateUrl: './listar-articulo.component.html',
  styleUrls: ['./listar-articulo.component.css']
})
export class ListarArticuloComponent implements OnInit {

  titulo: string = 'Listado de articulos:';
  articulos: Articulo[];

  constructor(private articuloService: ArticuloService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getAllArticulos();
  }

  getAllArticulos() {
    this.articuloService.getAllArticulos().subscribe(data =>{
      this.articulos = data;
    });
  }

  editarArticulo(id: number): void {
    this.router.navigate(['nuevo-articulo', id]);
  }

}
