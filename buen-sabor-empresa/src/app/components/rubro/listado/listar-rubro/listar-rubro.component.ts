import { Component, OnInit } from '@angular/core';
import { Rubro } from 'src/app/models/rubro';
import { RubroService } from 'src/app/services/rubro.service';

@Component({
  selector: 'app-listar-rubro',
  templateUrl: './listar-rubro.component.html',
  styleUrls: ['./listar-rubro.component.css']
})
export class ListarRubroComponent implements OnInit {

  titulo: string = 'Lista de rubros';
  rubros: Rubro[];
  constructor(private rubroService: RubroService) { }

  ngOnInit(): void {
    this.listar()
  }
  listar():void {
    this.rubroService.getAll().subscribe((data) => {
      console.log(data)
      this.rubros = data
    })
  }
}
