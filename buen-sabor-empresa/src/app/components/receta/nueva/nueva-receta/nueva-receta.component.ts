import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { RecetaService } from 'src/app/services/receta.service';
import { ArticuloService } from 'src/app/services/articulo.service';
import { AedService } from 'src/app/services/aed.service';
import { UnidadMedidaService } from 'src/app/services/unidadMedida.service';
import { AlertaService } from 'src/app/services/alerta.service'; 

import { Receta } from 'src/app/models/receta';
import { RecetaForm } from 'src/app/models/recetaForm';
import { Articulo } from 'src/app/models/articulo';

@Component({
  selector: 'app-nueva-receta',
  templateUrl: './nueva-receta.component.html',
  styleUrls: ['./nueva-receta.component.css']
})
export class NuevaRecetaComponent implements OnInit {

  articulos: Articulo[];

  idItem = this.activatedRoute.snapshot.paramMap.get('id1');
  idArticulo = this.activatedRoute.snapshot.paramMap.get('id2');

  recetaForm = new FormGroup({
    id: new FormControl(''),
    cantidad: new FormControl('', Validators.required),
    articulo: new FormControl('', Validators.required),
    unidadMedida: new FormControl({value: '', disabled: true}, Validators.required)
  });

  constructor(private recetaService: RecetaService, private articuloService: ArticuloService, private aedService: AedService, private unidadMedidaService: UnidadMedidaService, private alerta: AlertaService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getArticulosInsumo();
    
    if(this.idItem != "0") {
      this.recetaService.getRecetaById(this.idItem).subscribe(data =>{
        this.recetaForm.setValue({
          'id': data.id,
          'cantidad': data.cantidad,
          'articulo': data.articulo.id,
          'unidadMedida': data.unidadMedida.denominacion
        });
      });
    }
  }

  getArticulosInsumo() {
    this.articuloService.getArticulosByTipo(1).subscribe(data =>{
      this.articulos = data;
    });
  }

  capturarValor(event: any) {
    let id = event.target.value;
    this.articuloService.getArticuloById(id).subscribe(data =>{
      this.recetaForm.patchValue({'unidadMedida': data.unidadMedida.denominacion});
    });
  }

  async postForm(form: RecetaForm) {
    this.aedService.getAedByIdArticulo(this.idArticulo).subscribe(aed =>{
      this.articuloService.getArticuloById(form.articulo).subscribe(articulo =>{
        let receta: Receta = { "id": +(this.idItem)!, "cantidad": form.cantidad, "articulo": articulo,
        "articuloElaboradoDetalle": aed, "unidadMedida": articulo.unidadMedida };
        this.recetaService.saveReceta(receta).subscribe(data =>{
          if(data == null) {
            this.alerta.mostrarError("No se pudo guardar la receta!", "Error");
          } else {
            this.alerta.mostrarSuccess("Receta guardada!", "Hecho");
          }
        });
      });
    });
    await this.router.navigate(['recetas']);
  }

  async deleteItem() {
    this.recetaService.deleteRecetaById(this.idItem).subscribe(data =>{
      if(data == null) {
        this.alerta.mostrarError("No se pudo eliminar la receta!", "Error");
      } else {
        this.alerta.mostrarSuccess("Receta eliminada!", "Hecho");
      }
    });
    await this.router.navigate(['recetas']);
  }

}
