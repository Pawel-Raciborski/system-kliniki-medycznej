import { Injectable } from '@angular/core';
import {PatientDetails} from '../../patient/model/patient-details';
import {Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PatientDetailsService {

  constructor() { }

  updatePatientDetails(patientDetailsToUpdate: PatientDetails): Observable<PatientDetails> {
    return of(patientDetailsToUpdate);
  }
}
