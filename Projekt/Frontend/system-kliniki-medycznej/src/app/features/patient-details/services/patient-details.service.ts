import { Injectable } from '@angular/core';
import {PatientDetails} from '../../patient/model/patient-details';
import {Observable, of} from 'rxjs';
import {environment} from '../../../../environments/environment.dev';
import {HttpClient, HttpParams} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PatientDetailsService {
  url = `${environment.serverUrl}/patients-details`;

  constructor(
    private httpClient: HttpClient
  ) { }

  updatePatientDetails(patientDetailsToUpdate: PatientDetails, pesel?: string): Observable<PatientDetails> {
    console.log(patientDetailsToUpdate);
    if(!patientDetailsToUpdate.id){
      let params = new HttpParams();

      if(pesel){
        params = params.append('pesel', pesel);
      }

      return this.httpClient.post<PatientDetails>(
        `${this.url}/create`,
        patientDetailsToUpdate,
        {
          params
        }
      );
    }

    return this.httpClient.put<PatientDetails>(
      `${this.url}/update`,
      patientDetailsToUpdate
    );
  }
}
