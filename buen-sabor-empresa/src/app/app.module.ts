import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; 
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TipoArticuloComponent } from './components/tipo-articulo/tipo-articulo.component';
import { HeaderComponent } from './components/header/header.component';
import { InicioComponent } from './components/inicio/inicio.component';
import { RubroService } from './services/rubro.service';
import { ArticuloService } from './services/articulo.service';
import { TipoArticuloService } from './services/tipo-articulo.service';
import { ListarRubroComponent } from './components/rubro/listado/listar-rubro/listar-rubro.component';
import { NuevoRubroComponent } from './components/rubro/nuevo/nuevo-rubro/nuevo-rubro.component';
import { ListarArticuloComponent } from './components/articulo/listado/listar-articulo/listar-articulo.component';
import { NuevoArticuloComponent } from './components/articulo/nuevo/nuevo-articulo/nuevo-articulo.component';
@NgModule({
  declarations: [
    AppComponent,
    TipoArticuloComponent,
    HeaderComponent,
    InicioComponent,
    ListarRubroComponent,
    NuevoRubroComponent,
    ListarArticuloComponent,
    NuevoArticuloComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [RubroService, ArticuloService, TipoArticuloService],
  bootstrap: [AppComponent]
})
export class AppModule { }
