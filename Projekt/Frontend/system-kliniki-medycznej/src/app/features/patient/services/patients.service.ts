import { Injectable } from '@angular/core';
import {Observable, of, throwError} from 'rxjs';
import {PatientData} from '../model/patient-data';
import {AccountInfo} from '../../account/model/account-info';
import {PatientDetails} from '../model/patient-details';
import {Pagination} from '../../pagination/model/pagination';
import {SearchPatient} from '../model/search-patient';

@Injectable({
  providedIn: 'root'
})
export class PatientsService {
  patientData: PatientData[] = [
    {
      id: 1,
      personalDetails: {
        pesel: "90010112345",
        name: "John",
        surname: "Doe",
        birthDate: "1990-01-01",
        gender: "male",
        phoneNumber: "+48123456789",
        address: {
          street: "Main Street",
          apartmentNumber: "12A",
          postalCode: "00-123",
          city: "Warsaw",
        },
      },
      parentPesel: "70100112345",
    },
    {
      id: 2,
      personalDetails: {
        pesel: "92030367890",
        name: "Jane",
        surname: "Smith",
        birthDate: "1992-03-03",
        gender: "female",
        phoneNumber: "+48111222333",
        address: {
          street: "Second Avenue",
          apartmentNumber: "5B",
          postalCode: "00-456",
          city: "Krakow",
        },
      },
      parentPesel: "71030367890",
    },
    {
      id: 3,
      personalDetails: {
        pesel: "85060654321",
        name: "Adam",
        surname: "Kowalski",
        birthDate: "1985-06-06",
        gender: "male",
        phoneNumber: "+48123455678",
        address: {
          street: "Third Road",
          apartmentNumber: "7C",
          postalCode: "00-789",
          city: "Gdansk",
        },
      },
      parentPesel: "65060654321",
    },
    {
      id: 4,
      personalDetails: {
        pesel: "96070787654",
        name: "Eva",
        surname: "Nowak",
        birthDate: "1996-07-07",
        gender: "female",
        phoneNumber: "+48122334455",
        address: {
          street: "Fourth Lane",
          apartmentNumber: "9D",
          postalCode: "01-123",
          city: "Poznan",
        },
      },
      parentPesel: "70070787654",
    },
    {
      id: 5,
      personalDetails: {
        pesel: "88080812345",
        name: "Marek",
        surname: "Lewandowski",
        birthDate: "1988-08-08",
        gender: null,
        phoneNumber: "+48987654321",
        address: {
          street: "Fifth Avenue",
          apartmentNumber: "2E",
          postalCode: "01-456",
          city: "Wroclaw",
        },
      },
      parentPesel: "67080812345",
    }
  ];

  constructor() { }

  getAllPatientsPaged(pagination:Pagination) : Observable<PatientData[]>{
    return of(this.patientData);
  }

  findPatientData(id: number): Observable<PatientData> {
    console.log(typeof id)
    let patient = this.patientData.find(patient => patient.id === +id);
    console.log(patient);

    if(!patient){
      return throwError(() => new Error("Nie znaleziono pacjenta o podanym identyfikatorze"));
    }

    return of(patient);
  }

  findPatientAccount(id: number): Observable<AccountInfo> {
    let account: AccountInfo = {
      username: "jan_kowalski",
      email: "jan.kowalski@mail.com",
    };

    return of(account);
  }

  findPatientPesel(id: number):Observable<string> {
    return of("12473625161");
  }

  findPatientDetails(pesel: string): Observable<PatientDetails>{
    let patientPesel = {
      pesel: pesel
    };

    return of({
      id: 1,
      heightInCm: "178cm",
      weightInKg: '78kg',
      bloodType: "A"
    })
  }

  searchPatient(searchPatient: SearchPatient) : Observable<PatientData[]>{
    let patient = this.patientData.filter(p => p.personalDetails.pesel.startsWith(searchPatient.pesel));
    return of(patient);
  }
}
