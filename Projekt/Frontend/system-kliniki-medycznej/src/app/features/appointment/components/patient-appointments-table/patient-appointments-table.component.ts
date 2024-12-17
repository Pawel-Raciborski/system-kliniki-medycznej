import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {PatientAppointmentInfo} from '../../model/patient-appointment-info';
import {
  DoctorSearchBarComponent
} from '../../../doctor/components/search-bar/doctor-search-bar/doctor-search-bar.component';
import {DoctorsTableComponent} from '../../../doctor/components/doctor-table/doctors-table/doctors-table.component';
import {TableOptionsComponent} from '../../../doctor/components/doctor-table/table-options/table-options.component';
import {DatePipe} from '@angular/common';
import {PaginationBarComponent} from '../../../pagination/components/pagination-bar/pagination-bar.component';
import {MatPaginator} from '@angular/material/paginator';
import {MatCell, MatCellDef, MatColumnDef, MatHeaderCell, MatHeaderCellDef, MatTable} from '@angular/material/table';
import {
  BasicAppointmentDetailsDialogComponent
} from '../doctor-appointments/dialogs/basic-appoitment-details-dialog/basic-appointment-details-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-patient-appointments-table',
  standalone: true,
  imports: [
    DoctorSearchBarComponent,
    DoctorsTableComponent,
    TableOptionsComponent,
    DatePipe,
    PaginationBarComponent,
    MatPaginator,
    MatTable,
    MatColumnDef,
    MatHeaderCell,
    MatCell,
    MatCellDef,
    MatHeaderCellDef
  ],
  templateUrl: './patient-appointments-table.component.html',
  styleUrl: './patient-appointments-table.component.css'
})
export class PatientAppointmentsTableComponent {
  @Input({required: true}) patientAppointments!: PatientAppointmentInfo[];

  constructor(
    private dialog: MatDialog,
  ) {
  }

  showAppointmentDetails(appointmentInfo: PatientAppointmentInfo) {
    this.dialog.open(BasicAppointmentDetailsDialogComponent, {
      data: appointmentInfo.id
    });
  }
}
