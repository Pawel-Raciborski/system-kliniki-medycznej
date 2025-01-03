import {Routes} from '@angular/router';
import {AdminMainPageComponent} from '../components/admin-main-page/admin-main-page.component';
import {
  DoctorManagementPageComponent
} from '../components/doctor-management/doctor-management-page/doctor-management-page.component';
import {DoctorDetailsComponent} from '../components/doctor-info/doctor-details/doctor-details.component';
import {
  ReceptionistManagementPageComponent
} from '../components/receptionist/receptionist-management-page/receptionist-management-page.component';
import {
  ReceptionistProfileComponent
} from '../../receptionist/components/receptionist-profile/receptionist-profile.component';
import {PatientsManagementComponent} from '../../patient/components/patients-management/patients-management.component';
import {PatientProfileComponent} from '../../patient/components/patient-profile/patient-profile.component';
import {PermissionComponent} from '../../permission/components/permission/permission.component';
import {RolesComponent} from '../../roles/components/roles/roles.component';
import {PatientsComponent} from '../../patient/components/patients/patients.component';

export const adminRoutes: Routes = [
  {
    path:'',
    pathMatch:'full',
    component: AdminMainPageComponent
  },
  {
    path:'doctors',
    component: DoctorManagementPageComponent,
  },
  {
    path:'doctors/:id',
    component: DoctorDetailsComponent,

  },
  {
    path:'receptionists',
    component: ReceptionistManagementPageComponent
  },
  {
    path: 'receptionists/:id',
    component: ReceptionistProfileComponent,
  },
  {
    path:'patients',
    component: PatientsComponent
  },
  {
    path: 'patients/:id',
    component: PatientProfileComponent
  },
  {
    path: 'permissions',
    component: PermissionComponent
  },
  {
    path: 'roles',
    component: RolesComponent
  }
];
