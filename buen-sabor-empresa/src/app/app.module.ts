import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; 
import { HttpClientModule } from '@angular/common/http';

// Header
import { HeaderComponent } from './components/header/header.component';

// Inicio
import { InicioComponent } from './components/inicio/inicio.component';

// Rubro
import { RubroService } from './services/rubro.service';
import { ListarRubroComponent } from './components/rubro/listado/listar-rubro/listar-rubro.component';
import { NuevoRubroComponent } from './components/rubro/nuevo/nuevo-rubro/nuevo-rubro.component';

// Artículo
import { ArticuloService } from './services/articulo.service';
import { ListarArticuloComponent } from './components/articulo/listado/listar-articulo/listar-articulo.component';
import { NuevoArticuloComponent } from './components/articulo/nuevo/nuevo-articulo/nuevo-articulo.component';

// Tipo Artículo
import { TipoArticuloService } from './services/tipoArticulo.service';

@NgModule({
  declarations: [
    AppComponent,
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
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [RubroService, ArticuloService, TipoArticuloService],
  bootstrap: [AppComponent]
})
export class AppModule { }
