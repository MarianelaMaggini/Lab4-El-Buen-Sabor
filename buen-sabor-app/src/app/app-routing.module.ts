import { NgModule } from '@angular/core';

// Rutas
import { RouterModule, Routes } from '@angular/router';

// Inicio
import { HomeComponent } from './articulos/home/home.component';

import { PedidosComponent } from './pedidos/pedidos/pedidos.component';
import { AppGuardService } from './guard/app-guard.service';

const routes: Routes = [
  { path: 'home', loadChildren: () => import('./articulos/articulos.module').then(m => m.ArticulosModule ) },
  { path: 'auth', loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule) },
  { path: 'pedidos', loadChildren: () => import('./pedidos/pedidos.module').then(m => m.PedidosModule) },
  { path: '**', redirectTo:'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
