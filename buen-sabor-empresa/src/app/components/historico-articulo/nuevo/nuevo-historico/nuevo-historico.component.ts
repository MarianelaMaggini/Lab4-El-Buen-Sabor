import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { HistoricoArticuloService } from 'src/app/services/historico-articulo.service';
import { ArticuloService } from 'src/app/services/articulo.service';
import { InventarioService } from 'src/app/services/inventario.service';
import { AlertaService } from 'src/app/services/alerta.service'; 

import { HistoricoArticulo } from 'src/app/models/historico-articulo';
import { HistoricoArticuloForm } from 'src/app/models/historico-articuloForm';
import { Articulo } from 'src/app/models/articulo';
import { Inventario } from 'src/app/models/inventario';

@Component({
  selector: 'app-nuevo-historico',
  templateUrl: './nuevo-historico.component.html',
  styleUrls: ['./nuevo-historico.component.css']
})
export class NuevoHistoricoComponent implements OnInit {

  articulos: Articulo[];
  fechaActual = new Date();

  idArticulo = this.activatedRoute.snapshot.paramMap.get('id');

  historicoForm = new FormGroup({
    id: new FormControl(''),
    articulo: new FormControl('', Validators.required),
    fecha: new FormControl({value: '', disabled: true}, Validators.required),
    precioCompra: new FormControl('', Validators.required),
    cantidad: new FormControl('', Validators.required),
    unidadMedida: new FormControl({value: '', disabled: true}, Validators.required)
  });

  constructor(private historicoService: HistoricoArticuloService, private articuloService: ArticuloService, private inventarioService: InventarioService, private alerta: AlertaService, private router: Router, private activatedRoute: ActivatedRoute) { }
  
  ngOnInit(): void {
    this.getArticulosHistorico();
    this.getUnidad();
    this.historicoForm.patchValue({'articulo': this.idArticulo});
    this.historicoForm.patchValue({'fecha': this.fechaActual.toLocaleString()});
  }

  getArticulosHistorico() {
    this.articuloService.getArticulosByTipo(1).subscribe(insumos =>{
      this.articuloService.getArticulosByTipo(3).subscribe(noElaboradosPorNosotros =>{
        this.articulos = insumos.concat(noElaboradosPorNosotros);
      });
    });
  }

  capturarValor(event: any) {
    this.idArticulo = event.target.value;
    this.getUnidad();
  }

  getUnidad() {
    this.articuloService.getArticuloById(this.idArticulo).subscribe(articulo =>{
      this.historicoForm.patchValue({'unidadMedida': articulo.unidadMedida.denominacion})
    });
  }

  async postForm(form: HistoricoArticuloForm) {
    this.articuloService.getArticuloById(form.articulo).subscribe(articulo =>{
      let historicoArticulo: HistoricoArticulo = { "id": 0, "articulo": articulo,
      "fecha": this.fechaActual, "precioCompra": form.precioCompra, "cantidad": form.cantidad, 
      "unidadMedida": "" }
      this.historicoService.saveHistoricoArticulo(historicoArticulo).subscribe(data =>{
        if(data == null) {
          this.alerta.mostrarError("No se pudo guardar el registro!", "Error");
        } else {
          this.alerta.mostrarSuccess("Registro guardado!", "Hecho");
        }
      });
      this.inventarioService.getAllInventario().subscribe(data =>{
        let cantidad = 0;
        let resultado: any = data.filter(item => item.articulo.id == articulo.id);
        if(resultado.length == 0) {
          cantidad = form.cantidad;
          let inventarioS: Inventario = {"id": 0, "articulo": articulo, "stockMinimo": 1, "stockActual": cantidad};
          this.inventarioService.saveUpdateInventario(inventarioS).subscribe();
        } else {
          cantidad = resultado[0].stockActual + form.cantidad;
          let inventarioU: Inventario = {"id": resultado[0].id, "articulo": articulo, "stockMinimo": 1, "stockActual": cantidad};
          this.inventarioService.saveUpdateInventario(inventarioU).subscribe();
        }
      });
    });
    await this.router.navigate(['historico-articulos']);
  }

}
