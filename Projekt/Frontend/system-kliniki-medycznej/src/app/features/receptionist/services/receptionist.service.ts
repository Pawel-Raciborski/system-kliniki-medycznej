import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {ReceptionistInfo} from '../model/receptionist-info';
import {RegisterReceptionistForm} from '../model/register-receptionist-form';
import {ReceptionistDto} from '../model/receptionist-dto';
import {ReceptionistDetails} from '../model/receptionist-details';

@Injectable({
  providedIn: 'root'
})
export class ReceptionistService {

  constructor() { }

  get receptionists(): Observable<ReceptionistInfo[]>{
    const receptionistList: ReceptionistInfo[] = [
      {
        id: 1,
        name: "Emily",
        surname: "Watson",
        email: "emily.watson@example.com",
        phoneNumber: "+1-202-555-0136",
        dateOfEmployment: "2021-06-15",
      },
      {
        id: 2,
        name: "John",
        surname: "Doe",
        email: "john.doe@example.com",
        phoneNumber: "+1-202-555-0178",
        dateOfEmployment: "2020-09-22",
      },
      {
        id: 3,
        name: "Sophie",
        surname: "Miller",
        email: "sophie.miller@example.com",
        phoneNumber: "+44-1632-960437",
        dateOfEmployment: "2019-01-10",
      },
      {
        id: 4,
        name: "Carlos",
        surname: "Rodriguez",
        email: "carlos.rodriguez@example.com",
        phoneNumber: "+34-911-123456",
        dateOfEmployment: "2023-03-12",
      },
      {
        id: 5,
        name: "Aisha",
        surname: "Khan",
        email: "aisha.khan@example.com",
        phoneNumber: "+91-98112-34567",
        dateOfEmployment: "2022-11-01",
      }
    ];

    return of(receptionistList);
  }

  register(registerReceptionistForm: RegisterReceptionistForm): Observable<ReceptionistInfo> {
    let receptionistInfo: ReceptionistInfo = {
      id: 12,
      name: registerReceptionistForm.personalDetails.name,
      surname: registerReceptionistForm.personalDetails.surname,
      dateOfEmployment: registerReceptionistForm.dateOfEmployment,
      email: registerReceptionistForm.registerAccountData.email,
      phoneNumber: registerReceptionistForm.personalDetails.phoneNumber
    };
    return of(receptionistInfo);
  }

  findById(id: number) {
    console.log(`id: ${id}`);
    let receptionistDetails: ReceptionistDetails = {
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
      dateOfEmployment: '02-10-2024'
    }

    return of(receptionistDetails);
  }
}
