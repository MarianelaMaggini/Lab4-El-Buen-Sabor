import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ArticuloService } from 'src/app/services/articulo.service';
import { UnidadMedidaService } from 'src/app/services/unidadMedida.service';
import { RubroService } from 'src/app/services/rubro.service';
import { TipoArticuloService } from 'src/app/services/tipoArticulo.service';
import { FileService } from 'src/app/services/file.service';
import { AlertaService } from 'src/app/services/alerta.service'; 

import { Articulo } from 'src/app/models/articulo';
import { ArticuloForm } from 'src/app/models/articuloForm';
import { UnidadMedida } from 'src/app/models/unidad-medida';
import { Rubro } from 'src/app/models/rubro';
import { TipoArticulo } from 'src/app/models/tipo-articulo';

@Component({
  selector: 'app-nuevo-articulo',
  templateUrl: './nuevo-articulo.component.html',
  styleUrls: ['./nuevo-articulo.component.css']
})
export class NuevoArticuloComponent implements OnInit {

  unidadesMedida: UnidadMedida[];
  rubros: Rubro[];
  tiposArticulo: TipoArticulo[];
  imagenName: string;
  file: File;
  id = this.activatedRoute.snapshot.paramMap.get('id');
  fechaActual: Date;

  articuloForm = new FormGroup({
    id: new FormControl(''),
    denominacion: new FormControl('', Validators.required),
    imagen: new FormControl('', Validators.required),
    unidadMedida: new FormControl('', Validators.required),
    rubro: new FormControl('', Validators.required),
    tipoArticulo: new FormControl('', Validators.required)
  });

  constructor(private articuloService: ArticuloService, private unidadMedidaService: UnidadMedidaService, private rubroService: RubroService, private tipoArticuloService: TipoArticuloService, private fileService: FileService, private alerta: AlertaService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getCombos();

    if(this.id != "0") {
      this.articuloService.getArticuloById(this.id).subscribe(data =>{
        this.articuloForm.setValue({
          'id': data.id,
          'denominacion': data.denominacion,
          'imagen': null,
          'unidadMedida': data.unidadMedida.id,
          'rubro': data.rubro.id,
          'tipoArticulo': data.tipoArticulo.id
        });
      });
    }
  }

  getCombos() {
    this.unidadMedidaService.getAllUnidades().subscribe(data =>{
      this.unidadesMedida = data;
    });
    this.rubroService.getAllRubros().subscribe(data =>{
      this.rubros = data;
    });
    this.tipoArticuloService.getAllTiposArticulo().subscribe(data =>{
      this.tiposArticulo = data;
    });
  }

  capturarNombreImagen(event: any) {
    this.imagenName = event.target.files[0].name;
    this.file = event.target.files[0];
  }

  async postForm(form: ArticuloForm) {
    this.unidadMedidaService.getUnidadById(form.unidadMedida).subscribe(unidadMedida =>{
      this.rubroService.getRubroById(form.rubro).subscribe(rubro =>{
        this.tipoArticuloService.getTipoArticuloById(form.tipoArticulo).subscribe(tipoArticulo =>{
          let articulo: Articulo = { "id": +(this.id)!, "denominacion": form.denominacion, "imagen": this.imagenName, 
          "precioVenta": 0, "unidadMedida": unidadMedida, "rubro": rubro, "tipoArticulo": tipoArticulo, "fechaBaja": this.fechaActual };
          this.articuloService.saveArticulo(articulo).subscribe(data =>{
            if(data == null) {
              this.alerta.mostrarError("No se pudo guardar el artículo!", "Error");
            } else {
              this.alerta.mostrarSuccess("Artículo guardado!", "Hecho");
            }
          });
          this.fileService.upload(this.file).subscribe();
        });
      });
    });
    await this.router.navigate(['articulos']);
  }

  setFechaBaja(event: any) {
    if(event.target.checked) {
      this.fechaActual = new Date();
    }
  }

}
