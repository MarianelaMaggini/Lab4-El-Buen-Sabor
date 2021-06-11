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

// Artículo Elaborado Detalle
import { AedService } from './services/aed.service';
import { ListarAedComponent } from './components/articulo-elaborado-detalle/listado/listar-aed/listar-aed.component';
import { NuevoAedComponent } from './components/articulo-elaborado-detalle/nuevo/nuevo-aed/nuevo-aed.component';

// Tipo Artículo
import { TipoArticuloService } from './services/tipoArticulo.service';

// Receta
import { RecetaService } from './services/receta.service'
import { ListarRecetaComponent } from './components/receta/listado/listar-receta/listar-receta.component';
import { NuevaRecetaComponent } from './components/receta/nueva/nueva-receta/nueva-receta.component';

// Unidad de Medida
import { UnidadMedidaService } from './services/unidadMedida.service';

// File
import { FileService } from './services/file.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    InicioComponent,
    ListarRubroComponent,
    NuevoRubroComponent,
    ListarArticuloComponent,
    NuevoArticuloComponent,
    ListarAedComponent,
    NuevoAedComponent,
    ListarRecetaComponent,
    NuevaRecetaComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [RubroService, ArticuloService, TipoArticuloService, AedService, RecetaService, UnidadMedidaService, FileService],
  bootstrap: [AppComponent]
})
export class AppModule { }
