import { Component } from '@angular/core';
import {DoctorListComponent} from "../../doctor-management/doctor-list/doctor-list.component";
import {ReceptionistListComponent} from '../receptionist-list/receptionist-list.component';

@Component({
  selector: 'app-receptionist-management-page',
  standalone: true,
  imports: [
    DoctorListComponent,
    ReceptionistListComponent
  ],
  templateUrl: './receptionist-management-page.component.html',
  styleUrl: './receptionist-management-page.component.css'
})
export class ReceptionistManagementPageComponent {

}
