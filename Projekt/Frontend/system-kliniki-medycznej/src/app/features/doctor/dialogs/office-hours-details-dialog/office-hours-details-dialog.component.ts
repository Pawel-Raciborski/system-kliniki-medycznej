import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ReactiveFormsModule} from '@angular/forms';
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
export class OfficeHoursDetailsDialogComponent implements OnInit{
  constructor(
    @Inject(MAT_DIALOG_DATA) private doctorOfficeHoursDialogData: DoctorOfficeHoursDialogData,
              private dialogRef: MatDialogRef<OfficeHoursDetailsDialogComponent>) {
  }

  ngOnInit(): void {

  }

  onOfficeHoursSubmit() {
    this.dialogRef.close(
    {...this.doctorOfficeHoursDialogData.doctorOfficeHoursForm.value}
    );
  }

  get doctorOfficeHoursForm(){
    return this.doctorOfficeHoursDialogData.doctorOfficeHoursForm;
  }

  get isNewData(){
    return this.doctorOfficeHoursDialogData.isNewData;
  }

  onOfficeHoursCancel() {
    this.dialogRef.close();
  }
}
