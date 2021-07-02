import { NgModule } from '@angular/core';

// Rutas
import { RouterModule, Routes } from '@angular/router';

// Inicio
import { InicioComponent } from './components/inicio/inicio.component';

// Rubro
import { ListarRubroComponent } from './components/rubro/listado/listar-rubro/listar-rubro.component';
import { NuevoRubroComponent } from './components/rubro/nuevo/nuevo-rubro/nuevo-rubro.component';

// Artículo
import { ListarArticuloComponent } from './components/articulo/listado/listar-articulo/listar-articulo.component';
import { NuevoArticuloComponent } from './components/articulo/nuevo/nuevo-articulo/nuevo-articulo.component';

// Artículo Elaborado Detalle
import { ListarAedComponent } from './components/articulo-elaborado-detalle/listado/listar-aed/listar-aed.component';
import { NuevoAedComponent } from './components/articulo-elaborado-detalle/nuevo/nuevo-aed/nuevo-aed.component';

// Receta
import { ListarRecetaComponent } from './components/receta/listado/listar-receta/listar-receta.component';
import { NuevaRecetaComponent } from './components/receta/nueva/nueva-receta/nueva-receta.component';

// Histórico Artículo
import { ListarHistoricoComponent } from './components/historico-articulo/listado/listar-historico/listar-historico.component';
import { NuevoHistoricoComponent } from './components/historico-articulo/nuevo/nuevo-historico/nuevo-historico.component';

// Inventario
import { ListarInventarioComponent } from './components/inventario/listado/listar-inventario/listar-inventario.component';

// Pedido
import { ListarPedidoComponent } from './components/pedido/listado/listar-pedido/listar-pedido.component';

// Factura
import { ListarFacturaComponent } from './components/factura/listado/listar-factura/listar-factura.component';

// Usuario 
import { RegistroComponent } from './components/auth/registro/registro.component';
import { LoginComponent } from './components/auth/login/login.component';
import { LoginGuard } from './guard/login.guard';
import { ProdGuardService } from './guard/prod-guard.service';

const routes: Routes = [
  // Inicio
  {path:'', component: InicioComponent},

  // Login
  {path:'login', component: LoginComponent, canActivate: [LoginGuard]},

  // Registro usuario
  {path:'registro', component: RegistroComponent, canActivate: [LoginGuard]},

  // Rubro
  {path:'rubros', component: ListarRubroComponent, canActivate: [ProdGuardService], data: { expectedRol: ['admin', 'user']}},
  {path:'nuevo-rubro/:id', component: NuevoRubroComponent, canActivate: [ProdGuardService], data: { expectedRol: ['admin']}},

  // Articulo
  {path:'articulos', component: ListarArticuloComponent, canActivate: [ProdGuardService], data: { expectedRol: ['admin', 'user']}},
  {path:'nuevo-articulo/:id', component: NuevoArticuloComponent, canActivate: [ProdGuardService], data: { expectedRol: ['admin']}},

  // Artículo Elaborado Detalle
  {path:'aeds', component: ListarAedComponent, canActivate: [ProdGuardService], data: { expectedRol: ['admin', 'user']}},
  {path:'nuevo-aed/:id', component: NuevoAedComponent, canActivate: [ProdGuardService], data: { expectedRol: ['admin']}},

  // Receta
  {path:'recetas', component: ListarRecetaComponent, canActivate: [ProdGuardService], data: { expectedRol: ['admin', 'user']}},
  {path:'nueva-receta/:id1/:id2', component: NuevaRecetaComponent, canActivate: [ProdGuardService], data: { expectedRol: ['admin']}},

  // Histórico Artículo
  {path: 'historico-articulos', component: ListarHistoricoComponent, canActivate: [ProdGuardService], data: { expectedRol: ['admin', 'user']}},
  {path: 'nuevo-historico/:id', component: NuevoHistoricoComponent, canActivate: [ProdGuardService], data: { expectedRol: ['admin']}},

  // Inventario
  {path: 'inventario', component: ListarInventarioComponent, canActivate: [ProdGuardService], data: { expectedRol: ['admin', 'user']}},

  // Pedido
  {path: 'pedidos', component: ListarPedidoComponent, canActivate: [ProdGuardService], data: { expectedRol: ['admin', 'user']}},

  // Factura
  {path: 'facturas', component: ListarFacturaComponent, canActivate: [ProdGuardService], data: { expectedRol: ['admin', 'user']}},
  
  {path: '**', redirectTo:'', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
