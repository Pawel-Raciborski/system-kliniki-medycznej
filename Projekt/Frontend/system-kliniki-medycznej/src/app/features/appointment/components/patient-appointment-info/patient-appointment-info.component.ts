import {Component, Input} from '@angular/core';
import {PatientAppointmentInfo} from '../../model/patient-appointment-info';
import {DatePipe} from '@angular/common';
import {DoctorService} from '../../../doctor/services/doctor.service';
import {MatDialog} from '@angular/material/dialog';
import {DoctorInfoDialogComponent} from '../../../doctor/dialogs/doctor-info-dialog/doctor-info-dialog.component';

@Component({
  selector: 'app-patient-appointment-info',
  standalone: true,
  imports: [
    DatePipe
  ],
  templateUrl: './patient-appointment-info.component.html',
  styleUrl: './patient-appointment-info.component.css'
})
export class PatientAppointmentInfoComponent {
  @Input({required: true}) appointmentInfo!: PatientAppointmentInfo

  constructor(
    private dialog: MatDialog
  ) {
  }

  showAppointmentDoctorDetails() {

  }
}
this.dialog.open(DoctorInfoDialogComponent,{
  data: this.appointmentInfo.id,
  width: '700px'
});
