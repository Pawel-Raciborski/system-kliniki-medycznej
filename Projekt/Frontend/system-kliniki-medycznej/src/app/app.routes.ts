import { Routes } from '@angular/router';
import {MainPageComponent} from './features/main-page/components/main-page/main-page.component';
import {AdminPageComponent} from './features/admin/components/admin-page/admin-page.component';
import {adminRoutes} from './features/admin/routes/admin-routes';
import {PatientPanelComponent} from './features/patient/components/patient-panel/patient-panel.component';
import {patientRoutes} from './features/patient/routes/patients-route';
import {DoctorPanelComponent} from './features/doctor/components/doctor-panel/doctor-panel.component';
import {doctorRoutes} from './features/doctor/routes/doctor-routes';
import {
  ReceptionistPanelComponent
} from './features/receptionist-panel/components/receptionist-panel/receptionist-panel.component';
import {AuthComponent} from './features/auth/components/auth/auth.component';
import {roleAuthGuard} from './features/auth/guards/role-auth.guard';
import {receptionistRoutes} from './features/receptionist-panel/routes/receptionist-routes';
import {
  DoctorPatientCardComponent
} from './features/patient-card/components/doctor-patient-card/doctor-patient-card.component';
import {PatientCardComponent} from './features/patient-card/components/patient-card/patient-card.component';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'auth'
  },
  {
    path: 'auth',
    component: AuthComponent
  },
  {
    path: 'home',
    component: MainPageComponent,
  },
  {
    path: 'admin',
    component: AdminPageComponent,
    children: adminRoutes,
    // canActivate: [roleAuthGuard],
    // data: {
    //   role: "ADMIN"
    // }
  },
  {
    path:'patient-panel',
    component: PatientPanelComponent,
    children: patientRoutes,
    // canActivate: [roleAuthGuard],
    // data: {
    //   role: 'PATIENT'
    // }
  },
  {
    path:'doctor-panel',
    component: DoctorPanelComponent,
    children: doctorRoutes,
    // canActivate: [roleAuthGuard],
    // data: {
    //   role: 'DOCTOR'
    // }
  },
  {
    path: 'receptionist-panel',
    component: ReceptionistPanelComponent,
    children: receptionistRoutes
    // canActivate: [roleAuthGuard],
    // data: {
    //   role: 'RECEPTIONIST'
    // }
  },
];
