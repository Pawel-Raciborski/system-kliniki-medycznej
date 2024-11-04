import {Component, Input, OnInit} from '@angular/core';
import {PatientAppointmentInfo} from '../../../model/patient-appointment-info';
import {AppointmentService} from '../../../services/appointment.service';
import {PatientAppointmentsTableComponent} from '../../patient-appointments-table/patient-appointments-table.component';
import {PaginationBarComponent} from '../../../../pagination/components/pagination-bar/pagination-bar.component';
import {TableOptionsComponent} from '../../../../doctor/components/doctor-table/table-options/table-options.component';

@Component({
  selector: 'app-finished-appointments',
  standalone: true,
  imports: [
    PatientAppointmentsTableComponent,
    PaginationBarComponent,
    TableOptionsComponent
  ],
  templateUrl: './finished-appointments.component.html',
  styleUrl: './finished-appointments.component.css'
})
export class FinishedAppointmentsComponent implements OnInit {
  @Input({required: true}) patientPesel!: string;

  patientAppointments!: PatientAppointmentInfo[];
  paginationOptions: { page: number; pageSize: number } = {
    page: 0,
    pageSize: 10,
  };

  constructor(
    private appointmentService: AppointmentService
  ) {
  }

  ngOnInit(): void {
    this.loadFinishedAppointments();
  }

  updateFinishedAppointmentsPage(page: number) {
    if (page !== +this.paginationOptions.page) {
      this.paginationOptions.page = page;
      this.loadFinishedAppointments();
    }
  }

  private loadFinishedAppointments() {
    this.appointmentService.findPatientFinishedAppointments(this.patientPesel, this.paginationOptions).subscribe(
      data => {
        this.patientAppointments = data;
      }
    )
  }

  updateFinishedAppointmentPageSize(pageSize: number) {
    this.paginationOptions.pageSize = pageSize;
    this.loadFinishedAppointments();
  }

  hasArrayPageSize() {
    return this.patientAppointments.length !== +this.paginationOptions.pageSize;
  }
}
