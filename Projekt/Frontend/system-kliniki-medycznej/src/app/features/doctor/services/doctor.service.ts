import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {DoctorInfo} from '../domain/doctor-info';
import {DoctorDetails} from '../domain/doctor-details';

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

  findByPwzNumber(pwzNumber: number) {
    const doctorDetails: DoctorDetails = {
      username: "jan_kowalski",
      email: "jan.kowalski@mail.com",
      personalDetails: {
        pesel: "93817263611",
        name: "Jan",
        surname: "Kowalski",
        birthDate: "10-05-1993",
        gender: null,
        phoneNumber: "+48 726 361 631",
        address: {
          street: "Mickiewicza 6",
          apartmentNumber: "6/12",
          postalCode: "10-100",
          city: "Warszawa"
        }
      },
      description: "Lekarz z 5cio letnim doświadczeniem w Kardiologii. Do każdego pacjenta podchodzą z wyrozumiałością",
      doctorSpecializations: [
        {
          name: "Sercowe coś",
          description: "Specjalizacja w tym czyms",
          realizedDate: "01-01-2018"
        },
        {
          name: "Kardiologia",
          description: "5 letnie doświadczenie",
          realizedDate: "01-01-2018"
        }
      ],
      doctorOfficeHours: [
        {
          day: "PONIEDZIAŁEK",
          startHour: "09:00",
          endHour: "16:00",
          durationInMinutes: 30
        }
      ],
      pwzNumber: "245183",
      dateOfEmployment: "02-10-2024"
    }

    return of(doctorDetails);
  }
}
