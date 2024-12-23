import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {PatientData} from '../model/patient-data';
import {AccountInfo} from '../../account/model/account-info';
import {PatientDetails} from '../model/patient-details';
import {Pagination} from '../../pagination/model/pagination';
import {SearchPatient} from '../model/search-patient';
import {CreatePatientRequest} from '../model/create-patient-request';
import {PersonalDetails} from '../../personal-details/domain/personal-details';
import {environment} from '../../../../environments/environment.dev';
import {HttpClient} from '@angular/common/http';
import {CreateAccountRequest} from '../../account/model/create-account-request';

@Injectable({
  providedIn: 'root'
})
export class PatientsService {
  private url = `${environment.serverUrl}/patients`;
  private patientDetailsUrl = `${environment.serverUrl}/patients-details`;

  constructor(
    private httpClient: HttpClient
  ) { }

  getAllPatientsPaged(pagination:Pagination) : Observable<PatientData[]>{
    return this.httpClient.get<PatientData[]>(
      `${this.url}/all`,
      {
        params: {
          page: pagination.page,
          pageSize: pagination.pageSize
        }
      }
    );
  }

  findPatientData(id: number): Observable<PatientData> {
    return this.httpClient.get<PatientData>(
      `${this.url}/${id}`
    );
  }

  findPatientAccount(id: number): Observable<AccountInfo> {
    return this.httpClient.get<AccountInfo>(
      `${this.url}/${id}/account`
    );
  }

  findPatientPesel(id: number):Observable<PatientData> {
    return this.findPatientData(id);
  }

  findPatientDetails(pesel: string): Observable<PatientDetails>{
    return this.httpClient.get<PatientDetails>(
      `${this.patientDetailsUrl}`,
      {
        params: {
          pesel: pesel
        }
      }
    );
  }

  searchPatient(searchPatient: SearchPatient) : Observable<PatientData[]>{
    return this.httpClient.get<PatientData[]>(
      `${this.url}/search`,
      {
        params: {
          pesel: searchPatient.pesel
        }
      }
    );
  }

  buildPatientData(patientPesel: string, personalDetails:PersonalDetails) : CreatePatientRequest {
    return {
      parentPesel: patientPesel,
      personalDetails: personalDetails
    }
  }

  createPatient(patientToCreate: CreatePatientRequest) : Observable<PatientData> {
    return this.httpClient.post<PatientData>(
      `${this.url}/register`,
      patientToCreate
    );
  }

  deleteById(id: number): Observable<any> {
    return of();
  }

  createPatientAccount(accountToCreate: CreateAccountRequest): Observable<any> {
    return this.httpClient.post(
      `${this.url}/create-account`,
      accountToCreate
    )
  }
}
