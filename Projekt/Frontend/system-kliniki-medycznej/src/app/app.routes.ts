import { Routes } from '@angular/router';
import {MainPageComponent} from './features/main-page/components/main-page/main-page.component';
import {AdminPageComponent} from './features/admin/components/admin-page/admin-page.component';
import {adminRoutes} from './features/admin/routes/admin-routes';

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
  }
];
