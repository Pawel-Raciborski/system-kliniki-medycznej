import {Routes} from '@angular/router';
import {DoctorDetailsComponent} from '../../admin/components/doctor-info/doctor-details/doctor-details.component';
import {CalendarComponent} from '../components/calendar/calendar.component';
import {AppointmentsCalendarComponent} from '../components/appointments-calendar/appointments-calendar.component';
import {AppointmentComponent} from '../../appointment/components/doctor-appointments/appointment/appointment.component';
import {AvailableDoctorsComponent} from '../components/available-doctors/available-doctors.component';
import {PatientsComponent} from '../../patient/components/patients/patients.component';
import {
  DoctorPatientCardComponent
} from '../../patient-card/components/doctor-patient-card/doctor-patient-card.component';
import {
  DoctorPrescriptionsComponent
} from '../../prescriptions/components/doctor-prescriptions/doctor-prescriptions.component';
import {MedicinesComponent} from '../../medicine/components/medicines/medicines.component';

export const doctorRoutes: Routes = [
  {
    path: '',
    redirectTo: 'calendar',
    pathMatch: 'full'
  },
  {
    path: 'profile/:id',
    component: DoctorDetailsComponent
  },
  {
    path: 'calendar',
    component: AppointmentsCalendarComponent
  },
  {
    path: 'appointments/:id',
    component: AppointmentComponent
  },
  {
    path: 'doctors',
    component: AvailableDoctorsComponent
  },
  {
    path: 'patients',
    component: PatientsComponent,
  },
  {
    path: 'patients/:id/patient-card',
    component: DoctorPatientCardComponent
  },
  {
    path: 'prescriptions',
    component: DoctorPrescriptionsComponent
  },
  {
    path: 'medicines',
    component: MedicinesComponent
  }
];
