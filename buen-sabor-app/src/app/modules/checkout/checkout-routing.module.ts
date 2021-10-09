import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppGuardService } from 'src/app/guard/app-guard.service';
import { CheckoutComponent } from './checkout/checkout.component';

const routes: Routes = [
  { path: '', 
    children: [
    { path: '', component: CheckoutComponent, canActivate: [AppGuardService], data: { expectedRol: ['admin', 'user']} },
    {path: '**', redirectTo:'', pathMatch: 'full'}
  ]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CheckoutRoutingModule { }
