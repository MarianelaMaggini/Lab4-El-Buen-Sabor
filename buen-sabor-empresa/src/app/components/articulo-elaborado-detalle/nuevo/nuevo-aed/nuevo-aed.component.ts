import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { AedService } from 'src/app/services/aed.service'
import { ArticuloService } from 'src/app/services/articulo.service';

import { Aed } from 'src/app/models/aed';
import { AedForm } from 'src/app/models/aedForm';
import { Articulo } from 'src/app/models/articulo';

@Component({
  selector: 'app-nuevo-aed',
  templateUrl: './nuevo-aed.component.html',
  styleUrls: ['./nuevo-aed.component.css']
})
export class NuevoAedComponent implements OnInit {

  articulos: Articulo[];
  id = this.activatedRoute.snapshot.paramMap.get('id');

  aedForm = new FormGroup({
    id: new FormControl(''),
    descripcion: new FormControl('', Validators.required),
    tiempoEstimadoCocina: new FormControl('', Validators.required),
    articulo: new FormControl('', Validators.required)
  });

  constructor(private aedService: AedService, private articuloService: ArticuloService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getArticulosElaboradosPropios();

    if(this.id != "0") {
      this.aedService.getAedById(this.id).subscribe(data =>{
        this.aedForm.setValue({
          'id': data.id,
          'descripcion': data.descripcion,
          'tiempoEstimadoCocina': data.tiempoEstimadoCocina,
          'articulo': data.articulo.id
        });
      });
    }
  }

  getArticulosElaboradosPropios() {
    this.articuloService.getArticulosByTipo(2).subscribe(data =>{
      this.articulos = data;
    });
  }

  async postForm(form: AedForm) {
    this.articuloService.getArticuloById(form.articulo).subscribe(articulo =>{
      let aed: Aed = { "id": +(this.id)!, "descripcion": form.descripcion, "tiempoEstimadoCocina": form.tiempoEstimadoCocina,
      "articulo": articulo };
      this.aedService.saveAed(aed).subscribe();
    });
    await this.router.navigate(['aeds']);
  }

  async deleteAed() {
    this.aedService.deleteAedById(this.id).subscribe(data =>{
      console.log(data);
    });
    await this.router.navigate(['aeds']);
  }

}
