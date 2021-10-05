import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppGuardService } from '../guard/app-guard.service';
import { ArticuloDetalleComponent } from './articulo-detalle/articulo-detalle.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: '', 
    children: [
    { path: '', component: HomeComponent },
    { path: 'detalle/:id', component: ArticuloDetalleComponent, canActivate: [AppGuardService], data: { expectedRol: ['admin', 'user']} },
    {path: '**', redirectTo:'', pathMatch: 'full'}
  ]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ArticulosRoutingModule { }
