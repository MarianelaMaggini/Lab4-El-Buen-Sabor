import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Articulo } from '../../models/articulo';
import { ArticuloService } from '../../services/articulo.service';

@Component({
  selector: 'app-articulo-detalle',
  templateUrl: './articulo-detalle.component.html',
  styleUrls: ['./articulo-detalle.component.css'],
})
export class ArticuloDetalleComponent implements OnInit {
  articulo: Articulo;
  id: number;
  imagen: string;

  constructor(
    private articuloService: ArticuloService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.getArticuloById();
  }

  getArticuloById(): void {
    this.id = this.route.snapshot.params['id'];
    this.articuloService.getArticuloById(this.id).subscribe((data) => {
      this.articulo = data;
      this.imagen =
        'http://localhost:8080/upload/files/' + this.articulo.imagen;
    });
  }
}
