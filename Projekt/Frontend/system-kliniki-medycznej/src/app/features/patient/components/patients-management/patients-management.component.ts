import { Component } from '@angular/core';
import {HeaderTitleComponent} from "../../../header-title/header-title.component";
import {PatientListComponent} from '../patient-list/patient-list.component';

@Component({
  selector: 'app-patients-management',
  standalone: true,
  imports: [
    HeaderTitleComponent,
    PatientListComponent
  ],
  templateUrl: './patients-management.component.html',
  styleUrl: './patients-management.component.css'
})
export class PatientsManagementComponent {

}
