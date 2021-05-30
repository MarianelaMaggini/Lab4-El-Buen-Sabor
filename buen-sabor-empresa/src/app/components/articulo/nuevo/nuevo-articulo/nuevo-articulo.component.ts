import { Component, ComponentFactoryResolver, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ArticuloService } from 'src/app/services/articulo.service';
import { RubroService } from 'src/app/services/rubro.service';
import { TipoArticuloService } from 'src/app/services/tipoArticulo.service';

import { Articulo } from 'src/app/models/articulo';
import { Rubro } from 'src/app/models/rubro';
import { TipoArticulo } from 'src/app/models/tipo-articulo';

@Component({
  selector: 'app-nuevo-articulo',
  templateUrl: './nuevo-articulo.component.html',
  styleUrls: ['./nuevo-articulo.component.css']
})
export class NuevoArticuloComponent implements OnInit {

  articulo: Articulo;
  rubros: Rubro[];
  tiposArticulo: TipoArticulo[];
  id = +(this.activatedRoute.snapshot.queryParamMap.get('id'))!;

  articuloForm = new FormGroup({
    id: new FormControl(),
    denominacion: new FormControl('', Validators.required),
    imagen: new FormControl('', Validators.required),
    rubro: new FormControl('', Validators.required),
    tipoArticulo: new FormControl('', Validators.required)
  });

  constructor(private articuloService: ArticuloService, private rubroService: RubroService, private tipoArticuloService: TipoArticuloService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getAllRubrosAndTiposArticulo();

    if(this.id != 0) {
      this.articuloService.getArticuloById(this.id).subscribe(data =>{
        this.articulo = data;
        this.articuloForm.setValue({
          'id': data.id,
          'denominacion': data.denominacion,
          'imagen': data.imagen,
          'rubro': data.rubro.id,
          'tipoArticulo': data.tipoArticulo.id
        });
      });
    }
  }

  getAllRubrosAndTiposArticulo() {
    this.rubroService.getAllRubros().subscribe(data =>{
      this.rubros = data;
    });
    this.tipoArticuloService.getAllTiposArticulo().subscribe(data =>{
      this.tiposArticulo = data;
    });
  }

  async postForm(form: Articulo) {
    this.rubroService.getRubroById(form.rubro).subscribe(rubro =>{
      console.log(rubro);
      this.tipoArticuloService.getTipoArticuloById(form.tipoArticulo).subscribe(tipoArticulo =>{
        console.log(tipoArticulo);
        let articulo = { 'id': this.id, 'denominacion': form.denominacion, 'imagen': form.imagen, 
        'rubro': rubro, 'tipoArticulo': tipoArticulo };
        this.articuloService.saveArticulo(articulo).subscribe(data =>{
          console.log(data);
        });
      });
    });
    await this.router.navigate(['articulos']);
  }

  async deleteArticulo() {
    this.articuloService.deleteArticuloById(this.articulo.id).subscribe(data =>{
      console.log(data);
    });
    await this.router.navigate(['articulos']);
  }

}
