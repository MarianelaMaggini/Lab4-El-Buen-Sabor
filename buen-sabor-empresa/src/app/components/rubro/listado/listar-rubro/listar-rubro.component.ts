import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { RubroService } from 'src/app/services/rubro.service';

import { Rubro } from 'src/app/models/rubro';
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-listar-rubro',
  templateUrl: './listar-rubro.component.html',
  styleUrls: ['./listar-rubro.component.css']
})
export class ListarRubroComponent implements OnInit {

  titulo: string = 'Listado de rubros:';
  rubros: Rubro[];
  isLogged = false;
  isAdmin = false;
  
  constructor(private rubroService: RubroService, private router: Router, private tokenService: TokenService) { }

  ngOnInit(): void {
    this.isAdmin = this.tokenService.isAdmin();
    this.isLogged = this.tokenService.isLogged();
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
