import { Component } from '@angular/core';
import {DoctorListComponent} from '../doctor-list/doctor-list.component';
import {DoctorService} from '../../../../doctor/services/doctor.service';

@Component({
  selector: 'app-doctor-management-page',
  standalone: true,
  imports: [
    DoctorListComponent
  ],
  templateUrl: './doctor-management-page.component.html',
  styleUrl: './doctor-management-page.component.css'
})
export class DoctorManagementPageComponent {

  constructor(private doctorService: DoctorService) {
  }
}
