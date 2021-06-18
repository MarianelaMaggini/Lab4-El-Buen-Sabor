import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class AlertaService {

  constructor(private toast: ToastrService) { }
  
  mostrarSuccess(texto: string, titulo: string) {
    this.toast.success(texto, titulo);
  }

  mostrarError(texto: string, titulo: string) {
    this.toast.error(texto, titulo);
  }

}
