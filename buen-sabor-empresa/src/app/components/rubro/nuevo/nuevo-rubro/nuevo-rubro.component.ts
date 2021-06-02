import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { RubroService } from 'src/app/services/rubro.service';

import { Rubro } from 'src/app/models/rubro';

@Component({
  selector: 'app-nuevo-rubro',
  templateUrl: './nuevo-rubro.component.html',
  styleUrls: ['./nuevo-rubro.component.css']
})
export class NuevoRubroComponent implements OnInit {

  id = this.activatedRoute.snapshot.paramMap.get('id');

  rubroForm = new FormGroup({
    id: new FormControl(''),
    denominacion: new FormControl('', Validators.required)
  });

  constructor(private rubroService: RubroService, private router: Router, private activatedRoute: ActivatedRoute) { }

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
    this.rubroService.saveRubro(form).subscribe(data =>{
      console.log(data);
    });
    await this.router.navigate(['rubros']);
  }

  async deleteRubro() {
    this.rubroService.deleteRubroById(this.id).subscribe(data =>{
      console.log(data);
    });
    await this.router.navigate(['rubros']);
  }

}
