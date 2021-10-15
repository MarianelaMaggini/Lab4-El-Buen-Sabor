import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppGuardService } from 'src/app/guard/app-guard.service';
import { PerfilComponent } from './perfil/perfil.component';

const routes: Routes = [
  { path: '', 
  children: [
    { path: '', component: PerfilComponent, canActivate: [AppGuardService], data: { expectedRol: ['admin', 'user']}  },
    {path: '**', redirectTo:'', pathMatch: 'full'}
]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PerfilRoutingModule { }
