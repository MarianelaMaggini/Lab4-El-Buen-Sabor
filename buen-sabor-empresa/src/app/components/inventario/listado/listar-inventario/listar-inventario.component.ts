import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { InventarioService } from 'src/app/services/inventario.service'; 

import { Inventario } from 'src/app/models/inventario';

@Component({
  selector: 'app-listar-inventario',
  templateUrl: './listar-inventario.component.html',
  styleUrls: ['./listar-inventario.component.css']
})
export class ListarInventarioComponent implements OnInit {

  titulo: string = 'Inventario:';
  inventario: Inventario[];

  constructor(private inventarioService: InventarioService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.getInventario();
  }

  getInventario() {
    this.inventarioService.getAllInventario().subscribe(data =>{
      this.inventario = data;
    });
  }

}
