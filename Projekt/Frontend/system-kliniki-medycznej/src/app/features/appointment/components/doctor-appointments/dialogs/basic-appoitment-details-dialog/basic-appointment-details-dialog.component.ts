import {Component, Inject, OnInit} from '@angular/core';
import {AppointmentDetails} from '../../../../model/appointment-details';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {AppointmentService} from '../../../../services/appointment.service';
import {UserService} from '../../../../../auth/services/user.service';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-basic-appoitment-details-dialog',
  standalone: true,
  imports: [
    DatePipe
  ],
  templateUrl: './basic-appointment-details-dialog.component.html',
  styleUrl: './basic-appointment-details-dialog.component.css'
})
export class BasicAppointmentDetailsDialogComponent implements OnInit {
  appointmentDetails!: AppointmentDetails;

  constructor(
    @Inject(MAT_DIALOG_DATA) private appointmentId: string,
    private appointmentService: AppointmentService,
    private dialogRef: MatDialogRef<BasicAppointmentDetailsDialogComponent>
  ) {
  }

  ngOnInit(): void {
    this.appointmentService.getAppointmentDetails(this.appointmentId).subscribe(
      data => {
        this.appointmentDetails = data;
      }
    )
  }

  get patientData(){
    return this.appointmentDetails.patientData;
  }

  get appointment(){
    return this.appointmentDetails.appointment;
  }

  close() {
    this.dialogRef.close();
  }
}
