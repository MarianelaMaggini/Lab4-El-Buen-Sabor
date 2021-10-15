import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'home', loadChildren: () => import('./modules/articulos/articulos.module').then(m => m.ArticulosModule ) },
  { path: 'auth', loadChildren: () => import('./modules/auth/auth.module').then(m => m.AuthModule) },
  { path: 'pedidos', loadChildren: () => import('./modules/pedidos/pedidos.module').then(m => m.PedidosModule) },
  { path: 'checkout', loadChildren: () => import('./modules/checkout/checkout.module').then(m => m.CheckoutModule) }, 
  { path: 'perfil', loadChildren: () => import('./modules/perfil/perfil.module').then(m => m.PerfilModule) }, 
  { path: '**', redirectTo:'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
