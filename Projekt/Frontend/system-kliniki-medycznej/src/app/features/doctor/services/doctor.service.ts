import {Injectable, Optional} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse, HttpStatusCode} from '@angular/common/http';
import {EMPTY, Observable, of} from 'rxjs';
import {DoctorInfo} from '../domain/doctor-info';
import {DoctorDetails} from '../domain/doctor-details';
import {DoctorSearchOptions} from '../components/search-bar/model/doctor-search-options';
import {environment} from '../../../../environments/environment.dev';
import {RegisterDoctorForm} from '../domain/register-doctor-form';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {
  private apiUrl = `${environment.serverUrl}/doctors`;

  constructor(private httpClient: HttpClient) {
  }

  getPagedDoctors(paginationOptions: { page: number; pageSize: number }): Observable<DoctorInfo[]> {
    const doctors: DoctorInfo[] = [
      {
        id: 1,
        name: 'John',
        surname: 'Doe',
        email: 'john.doe@example.com',
        pwzNumber: '123456',
        phoneNumber: '555-1234',
        description: 'Expert in cardiology with 15 years of experience.',
        profileImagePath: 'assets/images/doctor_profile_image.webp'
      },
      {
        id: 2,
        name: 'Jane',
        surname: 'Smith',
        email: 'jane.smith@example.com',
        pwzNumber: '432142',
        phoneNumber: '555-5678',
        description: 'Specialist in neurology and brain surgery.',
        profileImagePath: 'assets/images/doctor_profile_image.webp'
      },
      {
        id: 3,
        name: 'Emily',
        surname: 'Johnson',
        email: 'emily.johnson@example.com',
        pwzNumber: '531342',
        phoneNumber: '555-9876',
        description: 'Experienced pediatrician with a focus on child healthcare.',
        profileImagePath: 'assets/images/doctor_profile_image.webp'
      }
    ];

    return this.httpClient.get<DoctorInfo[]>(
      `${this.apiUrl}/all`,
      {
        params: paginationOptions
      }
    );

    // return of(doctors);
  }

  findByPwzNumber(pwzNumber: number) {
    const doctorDetails: DoctorDetails = {
      username: "jan_kowalski",
      email: "jan.kowalski@mail.com",
      profileImagePath: "assets/images/doctor_profile_image.webp",
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
          id: 1,
          name: "Sercowe coś",
          description: "Specjalizacja w tym czyms",
          realizedDate: "01-01-2018"
        },
        {
          id: 2,
          name: "Kardiologia",
          description: "5 letnie doświadczenie",
          realizedDate: "01-01-2018"
        }
      ],
      doctorOfficeHours: [
        {
          id: 1,
          day: "PONIEDZIAŁEK",
          startHour: "09:00",
          endHour: "16:00",
          durationInMinutes: 30
        }
      ],
      pwzNumber: "245183",
      dateOfEmployment: "02-10-2024"
    };

    return this.httpClient.get<DoctorDetails>(
      `${this.apiUrl}/${pwzNumber}`
    );
    // return of(doctorDetails);
  }

  getAppointmentDoctorDetails(appointmentId: string): Observable<DoctorInfo> {
    let doctorDetails: DoctorInfo = {
      id: 1,
      name: "Jan",
      surname: "Kowalski",
      email: "jan.kowalski@mail.com",
      pwzNumber: '123456',
      phoneNumber: '555-1234',
      description: 'Expert in cardiology with 15 years of experience.',
      profileImagePath: ''
    };
    return of(doctorDetails);
  }

  getDoctorsWithSpecifiedOptions(doctorSearchOptions: DoctorSearchOptions): Observable<DoctorInfo[]> {
    console.log(doctorSearchOptions);

    return of([{
      id: 2,
      name: 'Jane',
      surname: 'Smith',
      email: 'jane.smith@example.com',
      pwzNumber: '432142',
      phoneNumber: '555-5678',
      description: 'Specialist in neurology and brain surgery.',
      profileImagePath: ''
    },
      {
        id: 3,
        name: 'Emily',
        surname: 'Johnson',
        email: 'emily.johnson@example.com',
        pwzNumber: '531342',
        phoneNumber: '555-9876',
        description: 'Experienced pediatrician with a focus on child healthcare.',
        profileImagePath: ''
      }]);
  }

  create(doctorToRegister: RegisterDoctorForm, profileImage: File | null): Observable<DoctorInfo> {
    const formData = new FormData();
    if(profileImage !== null){
      formData.append('profileImage', profileImage, profileImage.name);
    }
    formData.append('doctorToRegisterData',new Blob([JSON.stringify(doctorToRegister)],{type: 'application/json'}));

    let httpHeaders: HttpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type','multipart/form-data');

    return this.httpClient.post<DoctorInfo>(
      `${this.apiUrl}/create`,
      formData,
      {
        headers: httpHeaders
      }
    );

    // let doctorInfo: DoctorInfo = {
    //   id: 10,
    //   name: doctorToRegister.personalDetails.name,
    //   surname: doctorToRegister.personalDetails.surname,
    //   email: doctorToRegister.registerAccountData.email,
    //   phoneNumber: doctorToRegister.personalDetails.phoneNumber,
    //   pwzNumber: doctorToRegister.pwzNumber,
    //   description: doctorToRegister.description,
    //   profileImagePath: 'assets/images/doctor_profile_image.webp'
    // };
    // return of(doctorInfo)
  }

  delete(pwzNumber: number)  {
    return this.httpClient.delete(
      `${this.apiUrl}/delete`,
      {
        params: {
          pwzNumber: pwzNumber
        },
        observe: 'response'
      }
    );
  }
}
