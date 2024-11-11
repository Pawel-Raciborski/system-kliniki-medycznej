import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {DoctorOfficeHoursDialogData} from '../models/doctor-office-hours-dialog-data';

@Component({
  selector: 'app-office-hours-details-dialog',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './office-hours-details-dialog.component.html',
  styleUrl: './office-hours-details-dialog.component.css'
})
export class OfficeHoursDetailsDialogComponent {
  form: FormGroup = new FormGroup({
    day: new FormControl(''),
    startHour: new FormControl(''),
    endHour: new FormControl(''),
    durationInMinutes: new FormControl('')
  });

  constructor(
    @Inject(MAT_DIALOG_DATA) public doctorOfficeHoursDialogData: DoctorOfficeHoursDialogData,
              private dialogRef: MatDialogRef<OfficeHoursDetailsDialogComponent>) {

    if(doctorOfficeHoursDialogData.officeHours){
      this.form.patchValue(doctorOfficeHoursDialogData.officeHours);
    }
  }

  onOfficeHoursSubmit() {
    this.dialogRef.close(
    {...this.form.value}
    );
  }

  get isNewData(){
    return this.doctorOfficeHoursDialogData.isNewData;
  }

  onOfficeHoursCancel() {
    this.dialogRef.close();
  }
}
