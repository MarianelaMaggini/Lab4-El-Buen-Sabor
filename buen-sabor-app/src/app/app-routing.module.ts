import { NgModule } from '@angular/core';

// Rutas
import { RouterModule, Routes } from '@angular/router';

// Inicio
import { HomeComponent } from './modules/articulos/home/home.component';

// Login y Registro de usuario
import { LoginComponent } from './modules/auth/login/login.component';
import { RegistroComponent } from './modules/auth/registro/registro.component';
import { LoginGuard } from './guard/login.guard';

import { PedidosComponent } from './modules/pedidos/pedidos/pedidos.component';
import { AppGuardService } from './guard/app-guard.service';
import { ArticuloDetalleComponent } from './modules/articulos/articulo-detalle/articulo-detalle.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent, canActivate: [LoginGuard]},
  {path: 'registro', component: RegistroComponent, canActivate: [LoginGuard]},
  {path: 'pedidos', component: PedidosComponent, canActivate: [AppGuardService], data: { expectedRol: ['admin', 'user']}},
  {path: 'detalle/:id', component: ArticuloDetalleComponent},
  {path: '**', redirectTo:'', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
