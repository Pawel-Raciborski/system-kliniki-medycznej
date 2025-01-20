import {Routes} from '@angular/router';
import {AvailableDoctorsComponent} from '../../doctor/components/available-doctors/available-doctors.component';
import {PatientsComponent} from '../../patient/components/patients/patients.component';
import {PatientCardComponent} from '../../patient-card/components/patient-card/patient-card.component';
import {
  DoctorPatientCardComponent
} from '../../patient-card/components/doctor-patient-card/doctor-patient-card.component';
import {
  AppointmentsCalendarComponent
} from '../../doctor/components/appointments-calendar/appointments-calendar.component';
import {MedicinesComponent} from '../../medicine/components/medicines/medicines.component';
import {
  ReceptionistProfileComponent
} from '../../receptionist/components/receptionist-profile/receptionist-profile.component';
import {
  ReceptionistDoctorsAppointmentsCalendarComponent
} from '../../receptionist/components/receptionist-doctors-appointments-calendar/receptionist-doctors-appointments-calendar.component';

export const receptionistRoutes: Routes = [
  {
    path: 'doctors',
    component: AvailableDoctorsComponent
  },
  {
    path: 'patients',
    component: PatientsComponent
  },
  {
    path: 'patients/:id/patient-card',
    component: DoctorPatientCardComponent
  },
  {
    path: 'appointments',
    component: ReceptionistDoctorsAppointmentsCalendarComponent
  },
  {
    path: 'medicines',
    component: MedicinesComponent
  },
  {
    path: 'profile/:id',
    component: ReceptionistProfileComponent
  }
]
