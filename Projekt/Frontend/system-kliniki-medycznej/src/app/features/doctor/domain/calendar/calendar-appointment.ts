import {CalendarAppointmentInfo} from './calendar-appointment-info';

export interface CalendarAppointment {
  date: string;
  calendarAppointments: CalendarAppointmentInfo[];
}
