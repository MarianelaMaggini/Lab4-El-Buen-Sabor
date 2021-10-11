import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';

// Rutas
import { AppRoutingModule } from './app-routing.module';

// Peticiones HTTP
import { HttpClientModule } from '@angular/common/http';

// Formularios
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

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

// Histórico Artículo
import { HistoricoArticuloService } from './services/historico-articulo.service';
import { ListarHistoricoComponent } from './components/historico-articulo/listado/listar-historico/listar-historico.component';
import { NuevoHistoricoComponent } from './components/historico-articulo/nuevo/nuevo-historico/nuevo-historico.component';

// Inventario
import { InventarioService } from './services/inventario.service';
import { ListarInventarioComponent } from './components/inventario/listado/listar-inventario/listar-inventario.component';

// Pedido
import { PedidoService } from './services/pedido.service';
import { ListarPedidoComponent } from './components/pedido/listado/listar-pedido/listar-pedido.component';

// Detalle Pedido
import { DetallePedidoService } from './services/detalle-pedido.service';

// Factura
import { FacturaService } from './services/factura.service';  
import { ListarFacturaComponent } from './components/factura/listado/listar-factura/listar-factura.component';

// Detalle Factura
import { DetalleFacturaService } from './services/detalle-factura.service';

// Unidad de Medida
import { UnidadMedidaService } from './services/unidadMedida.service';

// File
import { FileService } from './services/file.service';

// Reporte
import { ReporteService } from './services/reporte.service';
import { ListarReporteComponent } from './components/reporte/listado/listar-reporte/listar-reporte.component';

// Alerta
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AlertaService } from './services/alerta.service';

// Usuario login y nuevo
import { LoginComponent } from './components/auth/login/login.component';
import { RegistroComponent } from './components/auth/registro/registro.component';
import { interceptorProvider } from './interceptors/prod-interceptor.service';

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
    ListarHistoricoComponent,
    NuevoHistoricoComponent,
    ListarInventarioComponent,
    ListarPedidoComponent,
    ListarFacturaComponent,
    LoginComponent,
    RegistroComponent,
    ListarReporteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [RubroService, ArticuloService, TipoArticuloService, AedService, RecetaService, HistoricoArticuloService, InventarioService, PedidoService, DetallePedidoService, FacturaService, DetalleFacturaService, UnidadMedidaService, FileService, ReporteService, AlertaService, interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
