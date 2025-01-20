import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {LocalStorageService} from '../../auth/services/local-storage.service';
import {HttpClient} from '@angular/common/http';
import {Pagination} from '../../pagination/model/pagination';
import {CardSummary} from '../model/card-summary';
import {PatientDiseaseHospitalizationInfo} from '../model/patient-disease-hospitalization-info';
import {HospitalizationInfo} from '../model/hospitalization-info';
import {environment} from '../../../../environments/environment.dev';

@Injectable({
  providedIn: 'root'
})
export class PatientCardService {
  url = `${environment.serverUrl}/patient-card`;

  constructor(
    private localStorageService: LocalStorageService,
    private httpClient: HttpClient
  ) {
  }

  findPatientCard(patientId: number): Observable<string> {
    console.log(patientId);
    return this.httpClient.get<string>(
      `${this.url}`,
      {
        params: {
          patientId: patientId
        },
      }
    );
  }

  findPatientCardWithPesel(pesel: string): Observable<any> {
    console.log(pesel);
    return of();
  }

  getCardSummary(patientCardId: string): Observable<CardSummary> {
    console.log(patientCardId);
    const exampleCardSummary: CardSummary = {
      fullName: "John Doe",
      pesel: "99010212345",
      birthDate: "1990-01-02",
      gender: "Male",
      patientDetails: {
        id: 1,
        weightInKg: "75",
        heightInCm: "180",
        bloodType: "O+",
      },
      numberOfDoctors: 3,
      numberOfAppointments: 12,
      numberOfPrescriptions: 5,
      numberOfHospitalizations: 2,
      nextAppointmentDate: "2024-01-15",
    };
    return this.httpClient.get<CardSummary>(
      `${this.url}/${patientCardId}/summary`
    );
  }
}
