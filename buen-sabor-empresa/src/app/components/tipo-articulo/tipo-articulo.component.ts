import { Component, OnInit } from '@angular/core';
import { TipoArticulo } from 'src/app/models/tipo-articulo';
import { TipoArticuloService } from 'src/app/services/tipo-articulo.service';

@Component({
  selector: 'app-tipo-articulo',
  templateUrl: './tipo-articulo.component.html',
  styleUrls: ['./tipo-articulo.component.css']
})
export class TipoArticuloComponent implements OnInit {
  titulo: string = 'Tipos de artículos';
  tipoArticulos: TipoArticulo[];
  constructor(private tipoArticuloService: TipoArticuloService) { }

  ngOnInit(): void {
    this.listar();
  }

  listar():void {
    this.tipoArticuloService.getAll().subscribe((data) => {
      console.log(data)
      this.tipoArticulos = data
    })
  }

}
