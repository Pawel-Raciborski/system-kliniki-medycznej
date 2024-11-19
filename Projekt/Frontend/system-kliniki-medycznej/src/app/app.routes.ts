import { Routes } from '@angular/router';
import {MainPageComponent} from './features/main-page/components/main-page/main-page.component';
import {AdminPageComponent} from './features/admin/components/admin-page/admin-page.component';
import {adminRoutes} from './features/admin/routes/admin-routes';
import {PatientPanelComponent} from './features/patient/components/patient-panel/patient-panel.component';
import {patientRoutes} from './features/patient/routes/patients-route';
import {DoctorPanelComponent} from './features/doctor/components/doctor-panel/doctor-panel.component';
import {doctorRoutes} from './features/doctor/routes/doctor-routes';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'home'
  },
  {
    path: 'home',
    component: MainPageComponent,
  },
  {
    path: 'admin',
    component: AdminPageComponent,
    children: adminRoutes
  },
  {
    path:'patient-panel',
    component: PatientPanelComponent,
    children: patientRoutes
  },
  {
    path:'doctor-panel',
    component: DoctorPanelComponent,
    children: doctorRoutes
  }
];
