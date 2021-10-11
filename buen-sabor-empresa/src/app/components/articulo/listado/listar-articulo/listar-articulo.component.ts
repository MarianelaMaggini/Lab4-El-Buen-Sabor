import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { ArticuloService } from 'src/app/services/articulo.service';

import { Articulo } from 'src/app/models/articulo';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-listar-articulo',
  templateUrl: './listar-articulo.component.html',
  styleUrls: ['./listar-articulo.component.css']
})
export class ListarArticuloComponent implements OnInit {

  titulo: string = 'Listado de articulos:';
  articulos: Articulo[];
  isLogged = false;
  isAdmin = false;

  constructor(private articuloService: ArticuloService, private router: Router, private tokenService: TokenService) { }

  ngOnInit(): void {
    this.isAdmin = this.tokenService.isAdmin();
    this.isLogged = this.tokenService.isLogged();
    this.getAllArticulosActivos();
  }

  getAllArticulosActivos() {
    this.articuloService.getAllArticulosActivos().subscribe(data =>{
      this.articulos = data;
    });
  }

  filterArticulos(event: any) {
    if(event.target.checked) {
      this.articuloService.getAllArticulos().subscribe(data =>{
        this.articulos = data;
      });
    } else {
      this.getAllArticulosActivos();
    }
  }

  editarArticulo(id: number): void {
    this.router.navigate(['nuevo-articulo', id]);
  }

}
