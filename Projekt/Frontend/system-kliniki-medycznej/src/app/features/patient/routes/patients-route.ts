import {Routes} from '@angular/router';
import {
  PatientAppointmentComponent
} from '../../appointment/components/patient-appointment/patient-appointment.component';
import {PatientProfileComponent} from '../components/patient-profile/patient-profile.component';
import {PatientCardComponent} from '../../patient-card/components/patient-card/patient-card.component';

export const patientRoutes: Routes = [
  {
    path: '',
    redirectTo: 'patient-card',
    pathMatch: 'full'
  },
  {
    path: 'appointments',
    component: PatientAppointmentComponent
  },
  {
    path: 'profile/:id',
    component: PatientProfileComponent
  },
  {
    path: 'patient-card',
    component: PatientCardComponent
  }
];
