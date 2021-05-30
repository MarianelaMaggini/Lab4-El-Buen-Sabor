import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { RubroService } from 'src/app/services/rubro.service';

import { Rubro } from 'src/app/models/rubro';

@Component({
  selector: 'app-listar-rubro',
  templateUrl: './listar-rubro.component.html',
  styleUrls: ['./listar-rubro.component.css']
})
export class ListarRubroComponent implements OnInit {

  titulo: string = 'Listado de rubros:';
  rubros: Rubro[];

  constructor(private rubroService: RubroService, private router: Router) { }

  ngOnInit(): void {
    this.getAllRubros();
  }

  getAllRubros() {
    this.rubroService.getAllRubros().subscribe(data =>{
      this.rubros = data;
    })
  }

  editarRubro(id: number): void {
    this.router.navigate(['nuevo-rubro', id]);
  }

}
