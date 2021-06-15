import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; 
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';

//external
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { ArticuloService } from './services/articulo.service';
import { CartComponent } from './components/cart/cart.component';
import { DetallePedidoComponent } from './components/detalle-pedido/detalle-pedido.component';
import { ArticulosComponent } from './components/articulos/articulos.component';
import { ItemArticuloComponent } from './components/item-articulo/item-articulo.component';
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    CartComponent,
    DetallePedidoComponent,
    ArticulosComponent,
    ItemArticuloComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot(), // ToastrModule added
  ],
  providers: [ArticuloService],
  bootstrap: [AppComponent]
})
export class AppModule { }
