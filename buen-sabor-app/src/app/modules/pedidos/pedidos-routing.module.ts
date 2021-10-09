import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppGuardService } from '../../guard/app-guard.service';
import { PedidosComponent } from './pedidos/pedidos.component';

const routes: Routes = [
  { path: '', 
  children: [
    { path: '', component: PedidosComponent, canActivate: [AppGuardService], data: { expectedRol: ['admin', 'user']}  },
    {path: '**', redirectTo:'', pathMatch: 'full'}
]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PedidosRoutingModule { }
