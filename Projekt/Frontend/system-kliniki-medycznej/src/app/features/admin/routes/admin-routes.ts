import {Routes} from '@angular/router';
import {AdminMainPageComponent} from '../components/admin-main-page/admin-main-page.component';
import {
  DoctorManagementPageComponent
} from '../components/doctor-management/doctor-management-page/doctor-management-page.component';

export const adminRoutes: Routes = [
  {
    path:'',
    pathMatch:'full',
    component: AdminMainPageComponent
  },
  {
    path:'doctor-management',
    component: DoctorManagementPageComponent
  }
];
