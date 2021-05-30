import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Inicio
import { InicioComponent } from './components/inicio/inicio.component';

// Rubro
import { ListarRubroComponent } from './components/rubro/listado/listar-rubro/listar-rubro.component';
import { NuevoRubroComponent } from './components/rubro/nuevo/nuevo-rubro/nuevo-rubro.component';

// Artículo
import { ListarArticuloComponent } from './components/articulo/listado/listar-articulo/listar-articulo.component';
import { NuevoArticuloComponent } from './components/articulo/nuevo/nuevo-articulo/nuevo-articulo.component';

const routes: Routes = [
  // Inicio
  {path:'', component: InicioComponent},

  // Rubro
  {path:'rubros', component: ListarRubroComponent},
  {path:'nuevo-rubro/:id', component: NuevoRubroComponent},

  // Articulo
  {path:'articulos', component: ListarArticuloComponent},
  {path:'nuevo-articulo/:id', component: NuevoArticuloComponent},
  
  {path: '**', redirectTo:'', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
