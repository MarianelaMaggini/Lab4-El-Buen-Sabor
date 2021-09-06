import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticuloElaboradoDetalleService } from 'src/app/services/articulo-elaborado-detalle.service';
import { RecetaElaboradoService } from 'src/app/services/receta-elaborado.service';
import { Articulo } from '../../models/articulo';
import { RecetaElaborado} from '../../models/receta-elaborado';
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
  idDetalle: number;
  recetasElaborados: RecetaElaborado[];


  constructor(
    private articuloService: ArticuloService,
    private route: ActivatedRoute,
    private articuloDetalleService: ArticuloElaboradoDetalleService,
    private recetaService: RecetaElaboradoService
  ) {}

  ngOnInit(): void {
    this.getArticuloById();
    this.getArticuloDetalleByArticuloId();
    this.getRecetaByArticuloDetalleId();
  }

  getArticuloById(): void {
    this.id = this.route.snapshot.params['id'];
    this.articuloService.getArticuloById(this.id).subscribe((data) => {
      this.articulo = data;
      this.imagen =
        'http://localhost:8080/upload/files/' + this.articulo.imagen;
    });
  }

  getArticuloDetalleByArticuloId(){
    this.articuloDetalleService.getArtElaboradoDetalleByArticuloId(this.id).subscribe((data) =>{
      this.idDetalle= data.id;
      console.log(data);
    })
  }

  getRecetaByArticuloDetalleId(){
    this.recetaService.getRecetaByArticuloDetalleId(this.idDetalle).subscribe((data)=>{
    this.recetasElaborados = data;
    })
  }
}
