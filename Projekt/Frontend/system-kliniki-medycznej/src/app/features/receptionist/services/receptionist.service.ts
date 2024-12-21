import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {ReceptionistInfo} from '../model/receptionist-info';
import {RegisterReceptionistForm} from '../model/register-receptionist-form';
import {ReceptionistDetails} from '../model/receptionist-details';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {environment} from '../../../../environments/environment.dev';

@Injectable({
  providedIn: 'root'
})
export class ReceptionistService {
  url = `${environment.serverUrl}/receptionist`;

  constructor(
    private httpClient: HttpClient
  ) { }

  get receptionists(): Observable<ReceptionistInfo[]>{
    return this.httpClient.get<ReceptionistInfo[]>(`${this.url}/all`);
  }

  register(registerReceptionistForm: RegisterReceptionistForm): Observable<ReceptionistInfo> {
    return this.httpClient.post<ReceptionistInfo>(
      `${this.url}/create`,
      registerReceptionistForm
    );
  }

  findById(id: number) {
    console.log(`id: ${id}`);
    let receptionistDetails: ReceptionistDetails = {
      username: "jan_kowalski",
      email: "jan.kowalski@mail.com",
      personalDetails: {
        id:1,
        pesel: "93817263611",
        name: "Jan",
        surname: "Kowalski",
        birthDate: "10-05-1993",
        gender: null,
        phoneNumber: "+48 726 361 631",
        address: {
          id: 1,
          street: "Mickiewicza 6",
          apartmentNumber: "6/12",
          postalCode: "10-100",
          city: "Warszawa"
        }
      },
      dateOfEmployment: '02-10-2024'
    }

    return this.httpClient.get<ReceptionistDetails>(
      `${this.url}/${id}`
    );
  }

  delete(receptionistId: number): Observable<HttpResponse<ReceptionistInfo>> {
    return this.httpClient.delete<ReceptionistInfo>(
      `${this.url}/${receptionistId}/delete`,
      {
        observe: 'response'
      }
    )
  }
}
