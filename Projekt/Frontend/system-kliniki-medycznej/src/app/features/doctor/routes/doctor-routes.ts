import {Routes} from '@angular/router';
import {DoctorDetailsComponent} from '../../admin/components/doctor-info/doctor-details/doctor-details.component';
import {CalendarComponent} from '../components/calendar/calendar.component';
import {AppointmentsCalendarComponent} from '../components/appointments-calendar/appointments-calendar.component';

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
  }
];
