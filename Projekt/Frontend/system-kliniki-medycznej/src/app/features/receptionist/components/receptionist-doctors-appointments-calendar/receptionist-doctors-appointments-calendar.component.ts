import {Component, OnInit} from '@angular/core';
import {DoctorService} from '../../../doctor/services/doctor.service';
import {Pagination} from '../../../pagination/model/pagination';
import {DoctorInfo} from '../../../doctor/domain/doctor-info';
import {
  DoctorSearchBarComponent
} from '../../../doctor/components/search-bar/doctor-search-bar/doctor-search-bar.component';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {CalendarComponent} from '../../../doctor/components/calendar/calendar.component';
import {CalendarService} from '../../../doctor/services/calendar.service';
import {AppointmentService} from '../../../appointment/services/appointment.service';
import {CalendarAppointmentsResponse} from '../../../doctor/domain/calendar/calendar-appointments-response';

@Component({
  selector: 'app-receptionist-doctors-appointments-calendar',
  standalone: true,
  imports: [
    DoctorSearchBarComponent,
    ReactiveFormsModule,
    CalendarComponent
  ],
  templateUrl: './receptionist-doctors-appointments-calendar.component.html',
  styleUrl: './receptionist-doctors-appointments-calendar.component.css'
})
export class ReceptionistDoctorsAppointmentsCalendarComponent implements OnInit{
  doctorCalendar!: CalendarAppointmentsResponse;
  pwzNumberControl = new FormControl();
  doctorId!:number;
    pagination:Pagination = {
      page: 0,
      pageSize:1000
    };
    availableDoctors: DoctorInfo[] = [];
    constructor(
      private doctorService: DoctorService,
      private appointmentService: AppointmentService
    ) {
    }
    ngOnInit(): void {
        this.doctorService.getPagedDoctors(this.pagination).subscribe(data => {
          this.availableDoctors = data;

          if(this.availableDoctors.length > 0){
            this.doctorId = this.availableDoctors[0].id;
            this.appointmentService.findDoctorAppointments('week',this.availableDoctors[0].id)
              .subscribe(data => {
                this.doctorCalendar = data;
              });
          }
        });
    }

  selectedPwzNumber() {
    if(this.pwzNumberControl && this.pwzNumberControl.value === ''){
      return false;
    }
    this.appointmentService.findDoctorAppointments('week',this.pwzNumberControl.value)
      .subscribe(data => {
        this.doctorCalendar = data;
      });
    return true;
  }

  selectedDoctor(data: Event) {
    const el = data.target as HTMLSelectElement;
    const id = Number(el.value);
    this.doctorId = id;
    this.appointmentService.findDoctorAppointments('week',id)
      .subscribe(data => {
        this.doctorCalendar = data;
      });
  }

  loadAppointments(date: string) {
      this.appointmentService.findDoctorAppointments(
        'week',
        this.doctorId,
        date
      ).subscribe(value => {
        this.doctorCalendar = value;
      })
  }
}
