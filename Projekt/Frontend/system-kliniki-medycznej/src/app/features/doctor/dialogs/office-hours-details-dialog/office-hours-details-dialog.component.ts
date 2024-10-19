import {Component, Inject, Input, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {OfficeHours} from '../../domain/office-hours';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';

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
  formGroup!: FormGroup;
  constructor(@Inject(MAT_DIALOG_DATA) public officeHourForm: FormGroup) {
  }

  ngOnInit(): void {
    // this.formGroup = this.formBuilder.group({
    //   day: this.officeHourForm.day,
    //   startHour: this.officeHourForm.startHour,
    //   endHour: this.officeHourForm.endHour,
    //   durationInMinutes: this.officeHourForm.durationInMinutes,
    // })
  }
}
