import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginGuard } from '../../guard/login.guard';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { LoginComponent } from './login/login.component';
import { RegistroComponent } from './registro/registro.component';
import { SendEmailComponent } from './send-email/send-email.component';

const routes: Routes = [
  { path: '', 
    children: [
    {path: 'login', component: LoginComponent, canActivate: [LoginGuard]},
    {path: 'registro', component: RegistroComponent, canActivate: [LoginGuard]},
    {path: 'send-email', component: SendEmailComponent, canActivate: [LoginGuard]},
    {path: 'change-password/:tokenPassword', component: ChangePasswordComponent, canActivate: [LoginGuard]},
    {path: '**', redirectTo:'login', pathMatch: 'full'}
  ]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
