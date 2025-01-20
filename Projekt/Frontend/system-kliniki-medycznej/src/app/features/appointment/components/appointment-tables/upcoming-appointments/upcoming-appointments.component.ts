import {Component, Input, OnInit} from '@angular/core';
import {PatientAppointmentInfo} from '../../../model/patient-appointment-info';
import {AppointmentService} from '../../../services/appointment.service';
import {PatientAppointmentsTableComponent} from '../../patient-appointments-table/patient-appointments-table.component';
import {PaginationBarComponent} from '../../../../pagination/components/pagination-bar/pagination-bar.component';
import {TableOptionsComponent} from '../../../../doctor/components/doctor-table/table-options/table-options.component';
import {UserService} from '../../../../auth/services/user.service';

@Component({
  selector: 'app-upcoming-appointments',
  standalone: true,
  imports: [
    PatientAppointmentsTableComponent,
    PaginationBarComponent,
    TableOptionsComponent
  ],
  templateUrl: './upcoming-appointments.component.html',
  styleUrl: './upcoming-appointments.component.css'
})
export class UpcomingAppointmentsComponent implements OnInit {
  patientId!: number;

  patientAppointments!: PatientAppointmentInfo[];
  paginationOptions: { page: number; pageSize: number } = {
    page: 0,
    pageSize: 10,
  };

  constructor(
    private appointmentService: AppointmentService,
    private userService: UserService
  ) {
  }

  ngOnInit(): void {
    this.patientId = this.userService.getId("patientId");

    this.appointmentService.findPatientUpcomingAppointments(this.patientId, this.paginationOptions)
      .subscribe(data => {
        console.log(data);
          this.patientAppointments = data;
        }
      );
  }

  loadUpcomingAppointments(pagination: { page: number; pageSize: number }) {
    this.appointmentService.findPatientUpcomingAppointments(this.patientId, pagination).subscribe(
      data => {
        this.patientAppointments = data;
        this.paginationOptions = pagination;
        console.log(this.paginationOptions);
      }
    );
  }

  updateAppointmentPageSize(newPageSize: number) {
    this.paginationOptions.pageSize = newPageSize;

    this.loadUpcomingAppointments(this.paginationOptions);
  }

  updateUpcomingAppointmentsPage(page: number) {
    if (page !== +this.paginationOptions.page) {
      this.paginationOptions.page = page;
      this.loadUpcomingAppointments(this.paginationOptions);
    }
  }

  hasArrayPageSize() {
    return this.patientAppointments.length !== +this.paginationOptions.pageSize;
  }
}
