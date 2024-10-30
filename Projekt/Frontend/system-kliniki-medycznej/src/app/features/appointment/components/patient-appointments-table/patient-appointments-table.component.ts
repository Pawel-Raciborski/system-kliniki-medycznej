import {Component, Input} from '@angular/core';
import {PatientAppointmentInfo} from '../../model/patient-appointment-info';
import {
  DoctorSearchBarComponent
} from '../../../doctor/components/search-bar/doctor-search-bar/doctor-search-bar.component';
import {DoctorsTableComponent} from '../../../doctor/components/doctor-table/doctors-table/doctors-table.component';
import {TableOptionsComponent} from '../../../doctor/components/doctor-table/table-options/table-options.component';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-patient-appointments-table',
  standalone: true,
  imports: [
    DoctorSearchBarComponent,
    DoctorsTableComponent,
    TableOptionsComponent,
    DatePipe
  ],
  templateUrl: './patient-appointments-table.component.html',
  styleUrl: './patient-appointments-table.component.css'
})
export class PatientAppointmentsTableComponent {
  @Input({required: true}) patientAppointments!: PatientAppointmentInfo[];
  @Input() page = 0;
  @Input() pageSize = 10;

}
