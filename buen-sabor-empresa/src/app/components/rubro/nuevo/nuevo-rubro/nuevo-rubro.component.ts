import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { RubroService } from 'src/app/services/rubro.service';
import { AlertaService } from 'src/app/services/alerta.service'; 

import { Rubro } from 'src/app/models/rubro';

@Component({
  selector: 'app-nuevo-rubro',
  templateUrl: './nuevo-rubro.component.html',
  styleUrls: ['./nuevo-rubro.component.css']
})
export class NuevoRubroComponent implements OnInit {

  id = this.activatedRoute.snapshot.paramMap.get('id');
  fechaActual: Date;

  rubroForm = new FormGroup({
    id: new FormControl(''),
    denominacion: new FormControl('', Validators.required),
  });

  constructor(private rubroService: RubroService, private alerta: AlertaService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    if(this.id != "0") {
      this.rubroService.getRubroById(this.id).subscribe(data =>{
        this.rubroForm.setValue({
          'id': data.id,
          'denominacion': data.denominacion
        });
      });
    }
  }

  async postForm(form: Rubro) {
    let rubro: Rubro = {"id": form.id, "denominacion": form.denominacion, "fechaBaja": this.fechaActual}
    this.rubroService.saveRubro(rubro).subscribe(data =>{
      if(data == null) {
        this.alerta.mostrarError("No se pudo guardar el rubro!", "Error");
      } else {
        this.alerta.mostrarSuccess("Rubro guardado!", "Hecho");
      }
    });
    await this.router.navigate(['rubros']);
  }

  setFechaBaja(event: any) {
    if(event.target.checked) {
      this.fechaActual = new Date();
    }
  }

}
