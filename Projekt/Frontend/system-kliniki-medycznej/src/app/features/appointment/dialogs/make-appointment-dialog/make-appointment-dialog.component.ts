import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {AppointmentService} from '../../services/appointment.service';
import {DoctorInfo} from '../../../doctor/domain/doctor-info';
import {
  DateFilterFn,
  MatCalendar,
  MatDatepicker,
  MatDatepickerInput, MatDatepickerInputEvent,
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
import {UserService} from '../../../auth/services/user.service';
import {DateFormatterService} from '../../../../services/date-formatter.service';
import {MessageDialogComponent} from '../../../message/message-dialog/message-dialog.component';

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

  workingDays: number[] = [];
  dayOfficeHours: string[] = [];
  patientPesel!: string;
  public pickedDate = false;

  constructor(
    @Inject(MAT_DIALOG_DATA) public doctorInfo: DoctorInfo,
    private appointmentService: AppointmentService,
    private officeHoursService: OfficeHoursService,
    private currentDialog: MatDialogRef<MakeAppointmentDialogComponent>,
    private patientService: PatientsService,
    public userService: UserService,
    private dialog: MatDialog,
    private dateFormatterService: DateFormatterService
  ) {
  }

  ngOnInit(): void {
    this.officeHoursService.findDoctorWorkingDays(this.doctorInfo.pwzNumber).subscribe(
      (data) => {
        this.workingDays = data;
      }
    );

    if (this.hasReceptionistRoleOrDoctor()) {
      this.appointmentForm.addControl("pesel", new FormControl(''));
    } else {
      this.patientService.findPatientPesel(this.userService.getId('patientId'))
        .subscribe(pesel => this.patientPesel = pesel.personalDetails.pesel);
    }
  }


  dateFilter = (date: Date | null): boolean => {
    const day = (date || new Date()).getDay();
    const today = new Date();

    if (!date) {
      return this.workingDays.includes(day);
    }
    return this.workingDays.includes(day) && date >= today;
  };

  public searchOfficeHoursForDay(date: string) {
    this.officeHoursService.findOfficeHoursForDay(this.doctorInfo.pwzNumber, this.formatDate(date, 'normal'))
      .subscribe(officeHours => {
        this.dayOfficeHours = officeHours;
      });
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

  public datePicked() {
    console.log("DATE PICKED");
    if (this.pickedDate) {
      this.searchOfficeHoursForDay(this.dateFormField.value);
      return true;
    }
    return false;
  }

  close() {
    this.currentDialog.close({
        appointmentCreated: false
      }
    );
  }

  makeAppointment() {
    let formatedDate = this.formatDate(this.date,'reversed');
    let pesel: string = this.hasReceptionistRoleOrDoctor() ? this.appointmentForm.value.pesel : this.patientPesel;

    this.appointmentService.createAppointment({
      patientPesel: pesel,
      date: formatedDate,
      hour: this.formHour,
      doctorPwzNumber: this.doctorInfo.pwzNumber
    }).subscribe({
      next: data => {
        this.currentDialog.close({
          appointmentCreated: true,
          date: this.date,
          hour: data.hour
        });
      },
      error: err => {
        this.dialog.open(MessageDialogComponent,{
          data: {
            message: err.error,
            type: 'error'
          }
        });
      }
    });
  }

  private hasReceptionistRoleOrDoctor() {
    return this.userService.hasRole("RECEPTIONIST") || this.userService.hasRole("DOCTOR");
  }

  formatDate(date: string, type: 'normal' | 'reversed') {
    let formattedDate = '';
    if(type === 'normal'){
      formattedDate = new DatePipe('pl').transform(date, 'dd-MM-yyyy') || '';
    }else if(type === 'reversed'){
      formattedDate = new DatePipe('pl').transform(date, 'yyyy-MM-dd') || '';
    }

    return formattedDate;
  }

  get formHour(): string {
    return this.appointmentForm.controls['hour'].value;
  }

  get date(): string {
    return this.appointmentForm.controls['date'].value;
  }

  datePicked2(matDatepickerInputEvent: MatDatepickerInputEvent<any, any>) {
    this.pickedDate = true;
    this.searchOfficeHoursForDay(matDatepickerInputEvent.value);
  }
}
