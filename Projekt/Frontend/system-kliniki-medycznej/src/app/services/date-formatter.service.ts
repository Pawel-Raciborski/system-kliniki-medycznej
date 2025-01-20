import {Injectable} from '@angular/core';
import {DatePipe} from '@angular/common';

@Injectable({
  providedIn: 'root',
})
export class DateFormatterService {

  constructor(
  ) {
  }

  parseDate(date: string) {
    const [day, month, year] = date.split('-').map(Number);
    return new Date(year, month - 1, day);
  }
}
