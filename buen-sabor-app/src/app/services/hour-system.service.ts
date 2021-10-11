import { Injectable } from '@angular/core';
import { Dia } from '../utils/dia';
import { Horario } from '../utils/horario';

@Injectable({
  providedIn: 'root'
})
export class HourSystemService {

  constructor() { }

  activeSystem(): boolean {
    const time = new Date();
    if (Dia.SABADO === time.getDay() && parseInt(Horario.HORA_INICIAL_SAB_DOM) <= time.getHours() && parseInt(Horario.HORA_FINAL_SAB_DOM) > time.getHours()) {
      return true;
    } else if (Dia.DOMINGO === time.getDay() && parseInt(Horario.HORA_INICIAL_SAB_DOM) <= time.getHours() && parseInt(Horario.HORA_FINAL_SAB_DOM) > time.getHours()) {
      return true;
    } else if (parseInt(Horario.HORA_INICIAL) <= time.getHours() && parseInt(Horario.HORA_FINAL) < time.getHours()) {
      return true;
    }else{
      return false;
    }
  }
}
