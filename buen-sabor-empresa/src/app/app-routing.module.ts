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

// Artículo Elaborado Detalle
import { ListarAedComponent } from './components/articulo-elaborado-detalle/listado/listar-aed/listar-aed.component';
import { NuevoAedComponent } from './components/articulo-elaborado-detalle/nuevo/nuevo-aed/nuevo-aed.component';

// Receta
import { ListarRecetaComponent } from './components/receta/listado/listar-receta/listar-receta.component';
import { NuevaRecetaComponent } from './components/receta/nueva/nueva-receta/nueva-receta.component';

const routes: Routes = [
  // Inicio
  {path:'', component: InicioComponent},

  // Rubro
  {path:'rubros', component: ListarRubroComponent},
  {path:'nuevo-rubro/:id', component: NuevoRubroComponent},

  // Articulo
  {path:'articulos', component: ListarArticuloComponent},
  {path:'nuevo-articulo/:id', component: NuevoArticuloComponent},

  // Artículo Elaborado Detalle
  {path:'aeds', component: ListarAedComponent},
  {path:'nuevo-aed/:id', component: NuevoAedComponent},

  // Receta
  {path:'recetas', component: ListarRecetaComponent},
  {path:'nueva-receta/:id1/:id2', component: NuevaRecetaComponent},
  
  {path: '**', redirectTo:'', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
