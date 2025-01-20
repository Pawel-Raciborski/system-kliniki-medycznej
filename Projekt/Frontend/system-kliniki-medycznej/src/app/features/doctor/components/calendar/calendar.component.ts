import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {DatePipe} from '@angular/common';
import {CalendarService} from '../../services/calendar.service';
import {MatCard} from '@angular/material/card';
import {MatCalendar, MatCalendarUserEvent} from '@angular/material/datepicker';
import {Router, RouterLink} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {ExceptionMessageComponent} from '../../../error/component/exception-message/exception-message.component';
import {
  BasicAppointmentDetailsDialogComponent
} from '../../../appointment/components/doctor-appointments/dialogs/basic-appoitment-details-dialog/basic-appointment-details-dialog.component';
import {DateFormatterService} from '../../../../services/date-formatter.service';
import {UserService} from '../../../auth/services/user.service';
import {MatMenu, MatMenuItem, MatMenuTrigger} from '@angular/material/menu';
import {AccountService} from '../../../account/services/account.service';
import {AppointmentService} from '../../../appointment/services/appointment.service';
import {CalendarAppointment} from '../../domain/calendar/calendar-appointment';

@Component({
  selector: 'app-calendar',
  standalone: true,
  imports: [
    DatePipe,
    MatCard,
    MatCalendar,
    RouterLink,
    MatMenu,
    MatMenuItem,
    MatMenuTrigger
  ],
  providers: [
    DatePipe
  ],
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.css'
})
export class CalendarComponent implements OnInit{
  @Input() events!: CalendarAppointment[];
  @Input() startDate!: string;
  @Input() endDate!: string;
  @Input() currentDay: string = "2024-11-20";
  @Output() loadDate = new EventEmitter<string>();
  start = new Date(this.startDate);
  end = new Date(this.endDate);
  days = [
    'PON',
    'WT',
    'ŚR',
    'CZW',
    'PIĄ'
  ];
  selected: string = "";

  constructor(
    private calendarService: CalendarService,
    private datePipe: DatePipe,
    private router: Router,
    private dialog: MatDialog,
    private userService: UserService,
    private appointmentService: AppointmentService
  ) {
  }

  ngOnInit(): void {
       this.start = new Date(this.startDate);
       this.end = new Date(this.endDate);
    }

  getDay(stringDate: string) {
    let date = new Date(stringDate);
    return date.getDay() -1;
  }

  openDate() {

  }

  isCurrentDay(date: string) {
    return date === this.currentDay;
  }

  setColor(status: string) {
    return this.calendarService.setColor(status);
  }

  onSelectDate(stringDate: string | null) {
    if(!stringDate){
      return;
    }
    let selectedDateString = this.formatDate(stringDate);

    if(!selectedDateString){
      return;
    }

    let selectedDate = new Date(selectedDateString);
    console.log(selectedDateString);
    console.log(this.start <= selectedDate);
    console.log(this.end >= selectedDate);
  }


  navigateToAppointment(app: any) {
    if((app.status !== 'Potwierdzono' && app.status !== 'W trakcie') || this.userService.hasRole("RECEPTIONIST")){
      this.dialog.open(BasicAppointmentDetailsDialogComponent,{
        data: app.id
      });
      return;
    }
    this.appointmentService.updateStatus(app.id,"IN_PROGRESS").subscribe(d => {
      app.status = d.status;
      this.router.navigate(["/doctor-panel/appointments",app.id]);
    });
  }

  loadPreviousWeek() {
    const today = new Date(this.startDate);
    const dayOfWeek = today.getDay();

    const daysToSubtract = dayOfWeek === 0 ? 6 + 7 : dayOfWeek - 1 + 7;
    const previousMonday = new Date(today);
    previousMonday.setDate(today.getDate() - daysToSubtract);

    this.loadCalendarData(this.formatDate(previousMonday.toDateString()));
  }

  loadNextWeek(){
    const today = new Date(this.startDate);
    const dayOfWeek = today.getDay();

    const daysToAdd = (8 - dayOfWeek) % 7 || 7;

    const nextMonday = new Date(today);
    nextMonday.setDate(today.getDate() + daysToAdd);

    this.loadCalendarData(this.formatDate(nextMonday.toDateString()));
  }

  private loadCalendarData(date: string) {
    this.loadDate.emit(date);
  }

  formatDate(date: string): string {
    return this.datePipe.transform(date, "dd-MM-yyyy") || "";
  }
}
