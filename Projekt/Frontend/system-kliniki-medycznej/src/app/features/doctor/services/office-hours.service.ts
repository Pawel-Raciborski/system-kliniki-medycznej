import { Injectable } from '@angular/core';
import {Observable, of, throwError} from 'rxjs';
import {OfficeHours} from '../domain/office-hours';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../../environments/environment.dev';

@Injectable({
  providedIn: 'root'
})
export class OfficeHoursService {
  private url = `${environment.serverUrl}/doctor/office-hours`;

  constructor(private httpClient: HttpClient) { }

  // Tutaj będzie przekazywane ID
  update(data: OfficeHours): Observable<OfficeHours> {
    return this.httpClient.put<OfficeHours>(
      `${this.url}/update`,
      data
    );
  }

  delete(officeHoursToRemove: OfficeHours): Observable<OfficeHours> {
    console.log(`Removing :`,officeHoursToRemove);
    return of(officeHoursToRemove);
  }

  create(officeHoursToCreate: OfficeHours, pwzNumber: string) : Observable<OfficeHours> {
    return this.httpClient.post<OfficeHours>(
      `${this.url}/add`,
      officeHoursToCreate,
      {
        params: {
          pwzNumber: pwzNumber
        }
      }
    );
  }


  findDoctorWorkingDays(pwzNumber: string) : Observable<number[]>{
    return this.httpClient.get<number[]>(
      `${this.url}/doctor-working-days`,
      {
        params: {
          pwzNumber: pwzNumber
        }
      }
    );
  }

  findOfficeHoursForDay(pwzNumber: string, date: string): Observable<string[]> {
    return this.httpClient.get<string[]>(
      `${this.url}/available-working-hours`,
      {
        params: {
          pwzNumber: pwzNumber,
          date: date
        }
      }
    )
  }
}
