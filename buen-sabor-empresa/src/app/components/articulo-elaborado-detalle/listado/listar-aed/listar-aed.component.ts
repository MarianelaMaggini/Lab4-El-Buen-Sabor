import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AedService } from 'src/app/services/aed.service'

import { Aed } from 'src/app/models/aed'
import { TokenService } from 'src/app/services/token.service';

@Component({
  selector: 'app-listar-aed',
  templateUrl: './listar-aed.component.html',
  styleUrls: ['./listar-aed.component.css']
})
export class ListarAedComponent implements OnInit {

  titulo: string = 'Listado de detalles:';
  aeds: Aed[];
  isLogged = false;
  isAdmin = false;
  constructor(
    private aedService: AedService, 
    private router: Router, 
    private activatedRoute: ActivatedRoute,
    private tokenService: TokenService
    ) { }

  ngOnInit(): void {
    this.isAdmin = this.tokenService.isAdmin();
    this.isLogged = this.tokenService.isLogged();
    this.getAllAed();
  }

  getAllAed() {
    this.aedService.getAllAed().subscribe(data =>{
      this.aeds = data;
    });
  }

  editarAed(id: number): void {
    this.router.navigate(['nuevo-aed', id]);
  }

}
