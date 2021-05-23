import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Articulo } from 'src/app/models/articulo';
import { Rubro } from 'src/app/models/rubro';
import { ArticuloService } from 'src/app/services/articulo.service';
import { RubroService } from 'src/app/services/rubro.service';

@Component({
  selector: 'app-listar-articulo',
  templateUrl: './listar-articulo.component.html',
  styleUrls: ['./listar-articulo.component.css']
})
export class ListarArticuloComponent implements OnInit {

  titulo: string = 'Lista de articulos asociados por rubro: ';
  articulos: Articulo[];
  rubro: Rubro;
  id: number;
  constructor(
    private articuloService: ArticuloService,
    private rubroService: RubroService,
    private route: ActivatedRoute,) { }

  ngOnInit(): void {
    this.listarPorIdRubro();
    this.cargarRubro();
  }

  listarPorIdRubro(): void {
    this.id = this.route.snapshot.params['id'];
    this.articuloService.getArticuloPorIdRubro(this.id).subscribe((data) => {
      this.articulos = data;
    })
  }

  cargarRubro(): void {
    this.rubroService.getRubroPorId(this.id).subscribe((rubro) => {
      this.rubro = rubro;
    });
  }

}
