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
}
