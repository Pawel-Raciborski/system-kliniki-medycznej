import {Component, OnInit} from '@angular/core';
import {CalendarComponent} from '../calendar/calendar.component';
import {CardSummaryComponent} from '../../../patient-card/components/card-summary/card-summary.component';
import {
  PatientHospitalizationComponent
} from '../../../patient-card/components/hospitalization/patient-hospitalization/patient-hospitalization.component';
import {AppointmentService} from '../../../appointment/services/appointment.service';
import {UserService} from '../../../auth/services/user.service';
import {CalendarAppointmentsResponse} from '../../domain/calendar/calendar-appointments-response';

@Component({
  selector: 'app-appointments-calendar',
  standalone: true,
  imports: [
    CalendarComponent,
    CardSummaryComponent,
    PatientHospitalizationComponent
  ],
  templateUrl: './appointments-calendar.component.html',
  styleUrl: './appointments-calendar.component.css'
})
export class AppointmentsCalendarComponent implements OnInit {
  calendarAppointments!: CalendarAppointmentsResponse;

  constructor(
    private appointmentService: AppointmentService,
    private userService: UserService
  ) {

  }


  ngOnInit(): void {
    if (this.userService.hasRole("DOCTOR")) {
      this.loadData('week', this.userService.getId("doctorId"));
    } else if (this.userService.hasRole("RECEPTIONIST")) {
      this.loadData('week');
    }
  }

  private loadData(formatType?: 'week' | 'month', id?: number, date?: string) {
    this.appointmentService.findDoctorAppointments(
      formatType,
      id,
      date
    ).subscribe(appointments => {
      this.calendarAppointments = appointments;
    });
  }

  loadAppointments(date: string) {
    if (this.userService.hasRole("DOCTOR")) {
      this.loadData('week', this.userService.getId("doctorId"), date);
    } else if (this.userService.hasRole("RECEPTIONIST")) {
      this.loadData('week', undefined, date);
    }
  }
}
