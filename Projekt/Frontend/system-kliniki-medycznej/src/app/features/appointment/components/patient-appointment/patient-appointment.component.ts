import {Component, OnInit} from '@angular/core';
import {PatientAppointmentInfo} from '../../model/patient-appointment-info';
import {AppointmentService} from '../../services/appointment.service';
import {UserService} from '../../../auth/services/user.service';
import {PatientAppointmentInfoComponent} from '../patient-appointment-info/patient-appointment-info.component';
import {PatientAppointmentsTableComponent} from '../patient-appointments-table/patient-appointments-table.component';
import {PatientsService} from '../../../patient/services/patients.service';
import {PaginationBarComponent} from "../../../pagination/components/pagination-bar/pagination-bar.component";
import {TableOptionsComponent} from '../../../doctor/components/doctor-table/table-options/table-options.component';
import {MatPaginator} from '@angular/material/paginator';
import {
  UpcomingAppointmentsComponent
} from '../appointment-tables/upcoming-appointments/upcoming-appointments.component';
import {
  FinishedAppointmentsComponent
} from '../appointment-tables/finished-appointments/finished-appointments.component';

@Component({
  selector: 'app-patient-appointment',
  standalone: true,
  imports: [
    PatientAppointmentInfoComponent,
    PatientAppointmentsTableComponent,
    PaginationBarComponent,
    TableOptionsComponent,
    MatPaginator,
    UpcomingAppointmentsComponent,
    FinishedAppointmentsComponent
  ],
  templateUrl: './patient-appointment.component.html',
  styleUrl: './patient-appointment.component.css'
})
export class PatientAppointmentComponent {

}
