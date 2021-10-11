import { Component, OnInit } from '@angular/core';
import { Dia } from 'src/app/utils/dia';
import { Horario } from 'src/app/utils/horario';

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
 
  constructor() { }

  ngOnInit(): void {
    
  }
  
 
}
