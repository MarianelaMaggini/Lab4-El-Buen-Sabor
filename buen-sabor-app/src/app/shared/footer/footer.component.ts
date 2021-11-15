import { Component, OnInit } from '@angular/core';
import { Horario } from 'src/app/utils/horario';
import { version  } from '../../../../package.json';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {
  horaInicial = Horario.HORA_INICIAL;
  horaFinal = Horario.HORA_FINAL;
  minuto = Horario.MINUTO;
  horaInicialSabDom = Horario.HORA_INICIAL_SAB_DOM;
  horaFinallSabDom = Horario.HORA_FINAL_SAB_DOM;
  version: string;
  constructor() {
    this.version = version;
   }

  ngOnInit(): void {
    
  }
  
 
}
