import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarArticuloComponent } from './components/articulo/listado/listar-articulo/listar-articulo.component';
import { InicioComponent } from './components/inicio/inicio.component';
import { ListarRubroComponent } from './components/rubro/listado/listar-rubro/listar-rubro.component';
import { TipoArticuloComponent } from './components/tipo-articulo/tipo-articulo.component';

const routes: Routes = [
  {path:'', component: InicioComponent},
  {path:'rubros', component: ListarRubroComponent},
  {path:'tipo-articulos', component: TipoArticuloComponent},
  {path:'articulos/:id', component: ListarArticuloComponent},
  {path: '**', redirectTo:'', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
