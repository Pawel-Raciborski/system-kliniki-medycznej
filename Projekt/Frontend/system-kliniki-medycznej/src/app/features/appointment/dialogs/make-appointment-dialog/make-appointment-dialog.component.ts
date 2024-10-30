import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {AppointmentService} from '../../services/appointment.service';
import {DoctorInfo} from '../../../doctor/domain/doctor-info';
import {
  DateFilterFn,
  MatCalendar,
  MatDatepicker,
  MatDatepickerInput,
  MatDatepickerToggle
} from '@angular/material/datepicker';
import {MatFormField, MatHint, MatLabel, MatSuffix} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {OfficeHoursService} from '../../../doctor/services/office-hours.service';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MatOption, MatSelect} from '@angular/material/select';
import {PatientsService} from '../../../patient/services/patients.service';
import {DatePipe} from '@angular/common';
import {LocalStorageService} from '../../../auth/services/local-storage.service';

@Component({
  selector: 'app-make-appointment-dialog',
  standalone: true,
  imports: [
    MatCalendar,
    MatDatepicker,
    MatFormField,
    MatDatepickerToggle,
    MatDatepickerInput,
    MatLabel,
    MatHint,
    MatInput,
    MatSuffix,
    ReactiveFormsModule,
    MatSelect,
    MatOption,
  ],
  templateUrl: './make-appointment-dialog.component.html',
  styleUrl: './make-appointment-dialog.component.css',
})
export class MakeAppointmentDialogComponent implements OnInit {
  appointmentForm: FormGroup = new FormGroup({
    date: new FormControl<string>(''),
    hour: new FormControl<string>('')
  });

  workingDayNumbersWithOfficeHours: { day: number, hours: string[] }[] = [];
  patientPesel!: string;

  constructor(
    @Inject(MAT_DIALOG_DATA) public doctorInfo: DoctorInfo,
    private appointmentService: AppointmentService,
    private officeHoursService: OfficeHoursService,
    private currentDialog: MatDialogRef<MakeAppointmentDialogComponent>,
    private patientService: PatientsService,
    private localStorageService: LocalStorageService
  ) {
  }

  ngOnInit(): void {
    this.officeHoursService.findDoctorWorkingDays(this.doctorInfo.pwzNumber).subscribe(
      (data) => {
        this.workingDayNumbersWithOfficeHours = data;
      }
    );

    this.patientService.findPatientPesel(this.localStorageService.getEmail())
      .subscribe(pesel => this.patientPesel = pesel);
  }


  dateFilter = (date: Date | null): boolean => {
    const day = (date || new Date()).getDay();
    const today = new Date();
    let days = this.workingDayNumbersWithOfficeHours.map(value => value.day);
    if (!date) {
      return days.includes(day);
    }
    return days.includes(day) && date >= today;
  };

  public officeHoursForDay(day: number) {
    let dayOfficeHours = this.workingDayNumbersWithOfficeHours.find(value => value.day === day);

    if (!dayOfficeHours) {
      return [];
    }

    return dayOfficeHours.hours;
  }

  get getDay() {
    let str = this.dateFormField.value;

    if (!str) {
      return new Date().getDay();
    }

    return new Date(str).getDay();
  }

  get dateFormField() {
    return this.appointmentForm.controls['date'] as FormControl;
  }

  close() {
    this.currentDialog.close({
        appointmentCreated: false
      }
    );
  }

  makeAppointment() {
    let formatedDate = this.formatDate(this.date);
    this.appointmentService.createAppointment({
      patientPesel: this.patientPesel,
      date: formatedDate,
      hour: this.formHour,
      doctorPwzNumber: this.doctorInfo.pwzNumber
    });
    this.currentDialog.close({
      appointmentCreated: true,
      date : formatedDate,
      hour : this.formHour
    });
  }

  formatDate(date: string) {
    let formatedDate: string = new DatePipe('pl').transform(date, 'dd-MM-yyyy') || '';
    console.log(date);

    return formatedDate;
  }

  get formHour(): string {
    return this.appointmentForm.controls['hour'].value;
  }

  get date(): string{
    return this.appointmentForm.controls['date'].value;
  }
}
