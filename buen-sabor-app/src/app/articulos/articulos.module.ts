import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ArticulosRoutingModule } from './articulos-routing.module';
import { ArticulosComponent } from './articulos/articulos.component';
import { ItemArticuloComponent } from './item-articulo/item-articulo.component';
import { ArticuloDetalleComponent } from './articulo-detalle/articulo-detalle.component';
import { NgxSpinnerModule } from 'ngx-spinner';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import { ToastrModule } from 'ngx-toastr';
import { HomeComponent } from './home/home.component';


@NgModule({
  declarations: [
    HomeComponent,
    ArticulosComponent,
    ItemArticuloComponent,
    ArticuloDetalleComponent
  ],
  imports: [
    CommonModule,
    ArticulosRoutingModule,
    ToastrModule.forRoot(), // ToastrModule added
    SweetAlert2Module.forRoot(),
    NgxSpinnerModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class ArticulosModule { }
