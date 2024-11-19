import {Routes} from '@angular/router';
import {DoctorDetailsComponent} from '../../admin/components/doctor-info/doctor-details/doctor-details.component';

export const doctorRoutes: Routes = [
  {
    path: 'profile/:id',
    component: DoctorDetailsComponent
  }
];
