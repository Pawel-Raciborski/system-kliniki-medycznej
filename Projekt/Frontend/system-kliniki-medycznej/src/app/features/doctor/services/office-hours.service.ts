import { Injectable } from '@angular/core';
import {Observable, of, throwError} from 'rxjs';
import {OfficeHours} from '../domain/office-hours';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OfficeHoursService {

  constructor(private httpClient: HttpClient) { }

  update(data: OfficeHours): Observable<OfficeHours> {
    let randomNumber = Math.floor(Math.random() * 11);
    console.log(randomNumber);
    if(randomNumber%2==0){
      console.log("dwada");
      return throwError(() => new Error("Mock backend error"));
    }

    return of(data);
  }

  delete(dayToRemove: string) {
    console.log(`Removing : [${dayToRemove}]`);
  }

  create(officeHoursToCreate: OfficeHours) : Observable<OfficeHours> {
    return of(officeHoursToCreate);
  }


  findDoctorWorkingDays(pwzNumber: string) : Observable<{day:number, hours: string[]}[]>{
    let workingDaysWithHours = [
      {
        day: 1,
        hours: ["09:00","09:30","11:30","13:30","16:00"]
      },
      {
        day: 2,
        hours: ["10:00","11:30","12:00","13:30","16:00"]
      },
      {
        day: 3,
        hours: ["13:30","14:00","15:30","16:00"]
      }
    ]
    return of(workingDaysWithHours);
  }
}
