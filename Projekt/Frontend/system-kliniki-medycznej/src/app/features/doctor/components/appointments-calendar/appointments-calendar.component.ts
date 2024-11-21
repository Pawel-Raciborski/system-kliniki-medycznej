import { Component } from '@angular/core';
import {CalendarComponent} from '../calendar/calendar.component';
import {CardSummaryComponent} from '../../../patient-card/components/card-summary/card-summary.component';
import {
  PatientHospitalizationComponent
} from '../../../patient-card/components/hospitalization/patient-hospitalization/patient-hospitalization.component';

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
export class AppointmentsCalendarComponent {

}
