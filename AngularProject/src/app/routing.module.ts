import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {CertificatesComponent} from './certificates/certificates.component';
import {AddCertificateComponent} from './add-certificate/add-certificate.component';
import {CertificateComponent} from './certificate/certificate.component';
import {AuthGuardService} from './auth/auth-guard.service';
import {AuthAdminGuardService} from './auth/auth-admin-guard.service';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: LoginComponent },
  { path: 'certificates', component: CertificatesComponent },
  { path: 'certificates/:id', component: CertificateComponent, canActivate: [AuthGuardService] },
  { path: 'add', component: AddCertificateComponent, canActivate: [AuthAdminGuardService] },
  { path: 'edit', component: AddCertificateComponent, canActivate: [AuthAdminGuardService] },
  { path: '', redirectTo: 'certificates', pathMatch: 'full' },
  { path: '**', redirectTo: 'certificates', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class RoutingModule { }
