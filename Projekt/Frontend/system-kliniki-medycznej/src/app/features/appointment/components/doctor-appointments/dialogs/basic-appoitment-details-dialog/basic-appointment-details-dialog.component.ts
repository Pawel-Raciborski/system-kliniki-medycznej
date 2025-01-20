import {Component, Inject, OnInit} from '@angular/core';
import {AppointmentDetails} from '../../../../model/appointment-details';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {AppointmentService} from '../../../../services/appointment.service';
import {UserService} from '../../../../../auth/services/user.service';
import {DatePipe} from '@angular/common';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-basic-appoitment-details-dialog',
  standalone: true,
  imports: [
    DatePipe,
    ReactiveFormsModule
  ],
  templateUrl: './basic-appointment-details-dialog.component.html',
  styleUrl: './basic-appointment-details-dialog.component.css'
})
export class BasicAppointmentDetailsDialogComponent implements OnInit {
  appointmentDetails!: AppointmentDetails;
  showUpdateStatusInput: boolean = false;
  availableStatuses : {value: string, name: string}[] = [];
  form: FormGroup = new FormGroup({
    status: new FormControl('')
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) private appointmentId: string,
    private appointmentService: AppointmentService,
    private dialogRef: MatDialogRef<BasicAppointmentDetailsDialogComponent>,
    public userService: UserService
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

  get doctor(){
    return this.appointmentDetails.doctor;
  }

  get appointment(){
    return this.appointmentDetails.appointment;
  }

  close() {
    this.dialogRef.close();
  }

  showUpdateStatus() {
    this.showUpdateStatusInput = !this.showUpdateStatusInput;

    if(this.showUpdateStatusInput){
      this.appointmentService.getAvailableStatuses().subscribe(data => {
        this.availableStatuses = data;
      });
    }
  }

  updateStatus() {
    console.log(this.form.value)
    this.appointmentService.updateStatus(this.appointmentId,this.form.value.status).subscribe(
      data => {
        console.log(data);
        this.appointmentDetails.appointment.status = data.status;
        this.showUpdateStatusInput = false;

      }
    )
  }
}
