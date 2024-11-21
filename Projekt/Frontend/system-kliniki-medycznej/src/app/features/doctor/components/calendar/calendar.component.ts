import {Component, Input} from '@angular/core';
import {DatePipe} from '@angular/common';
import {CalendarService} from '../../services/calendar.service';
import {MatCard} from '@angular/material/card';
import {MatCalendar, MatCalendarUserEvent} from '@angular/material/datepicker';

@Component({
  selector: 'app-calendar',
  standalone: true,
  imports: [
    DatePipe,
    MatCard,
    MatCalendar
  ],
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.css'
})
export class CalendarComponent {
  @Input() events: any[] = [
    {
      date: "2024-11-18",
      calendarAppointments: [
        {
          id: "ddd582b7-1f39-4118-b9e0-6ae1dc8e2d41",
          hour: "09:30",
          status: "Zakończona"
        },
        {
          id: "7ad87d8c-d3df-47dd-97a4-18c3a3ee473d",
          hour: "10:30",
          status: "Umówiono"
        },
        {
          id: "2053a517-5e9f-461d-b2ec-1034b4d3f9ea",
          hour: "13:00",
          status: "Potwierdzono"
        },
        {
          id: "2053a517-5e9f-461d-b2ec-1034b4d3f9ea",
          hour: "14:00",
          status: "Anulowano"
        }
      ]
    },
    {
      date: "2024-11-19",
      calendarAppointments: [
        {
          id: "5dbccd2d-93a2-48fd-ba37-1f5304d41d71",
          hour: "12:00",
          status: "Umówiono"
        }
      ]
    },
    {
      date: "2024-11-20",
      calendarAppointments: [
        {
          id: "60fb356f-69aa-4e28-ae8b-93dc900124fb",
          hour: "14:25",
          status: "Umówiono"
        }
      ]
    },
    {
      date: "2024-11-21",
      calendarAppointments: [
        {
          id: "40515823-e8da-47ce-af2a-4bafe217929e",
          hour: "08:00",
          status: "Anulowana"
        },
        {
          id: "050710aa-28bb-4ca4-b8d4-c7c49ef47735",
          hour: "09:30",
          status: "Umówiono"
        }
      ]
    },
    {
      date: "2024-11-22",
      calendarAppointments: []
    }
  ];
  @Input() startHour!: string;
  @Input() endHour!: string;
  start = new Date("2024-11-18");
  end = new Date("2024-11-22");
  currentDay = "2024-11-20";
  days = [
    'PON',
    'WT',
    'ŚR',
    'CZW',
    'PIĄ'
  ];
  selected: string = "";

  constructor(
    private calendarService: CalendarService
  ) {
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

  setColor(status: "Umówiono" |
    "Potwierdzono" |
    "Utworzono" |
    "W trakcie" |
    "Zakończona" |
    "Anulowana") {
    return this.calendarService.setColor(status);
  }

  onSelectDate(event: MatCalendarUserEvent<string | null>) {
    if(event.value){
      let date = new Date(event.value);
      console.log(date);
      console.log(event.value);
      console.log(this.start > date);
      console.log(this.end);
    }
  }
}
