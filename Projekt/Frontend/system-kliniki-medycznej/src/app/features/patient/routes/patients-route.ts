import {Routes} from '@angular/router';
import {
  PatientAppointmentComponent
} from '../../appointment/components/patient-appointment/patient-appointment.component';
import {PatientProfileComponent} from '../components/patient-profile/patient-profile.component';
import {PatientCardComponent} from '../../patient-card/components/patient-card/patient-card.component';
import {AvailableDoctorsComponent} from '../../doctor/components/available-doctors/available-doctors.component';

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
  },
  {
    path: 'doctors',
    component: AvailableDoctorsComponent
  }
];