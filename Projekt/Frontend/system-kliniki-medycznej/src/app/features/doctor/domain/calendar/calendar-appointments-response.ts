import {CalendarAppointment} from './calendar-appointment';

export interface CalendarAppointmentsResponse {
  calendarAppointments: CalendarAppointment[];
  start: string;
  end: string;
}
