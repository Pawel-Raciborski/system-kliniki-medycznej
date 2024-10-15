import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {DoctorInfo} from '../domain/doctor-info';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {
  constructor(private httpClient: HttpClient) { }

  getPagedDoctors(page: number,pageSize: number): Observable<DoctorInfo[]>{
    const doctors: DoctorInfo[] = [
      {
        id: 1,
        name: 'John',
        surname: 'Doe',
        email: 'john.doe@example.com',
        phoneNumber: '555-1234',
        description: 'Expert in cardiology with 15 years of experience.'
      },
      {
        id: 2,
        name: 'Jane',
        surname: 'Smith',
        email: 'jane.smith@example.com',
        phoneNumber: '555-5678',
        description: 'Specialist in neurology and brain surgery.'
      },
      {
        id: 3,
        name: 'Emily',
        surname: 'Johnson',
        email: 'emily.johnson@example.com',
        phoneNumber: '555-9876',
        description: 'Experienced pediatrician with a focus on child healthcare.'
      }
    ];

    return of(doctors);
  }
}
