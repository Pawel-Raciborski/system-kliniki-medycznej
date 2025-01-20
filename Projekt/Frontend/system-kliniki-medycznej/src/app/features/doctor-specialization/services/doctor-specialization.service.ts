import { Injectable } from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {DoctorSpecialization} from '../../doctor/domain/doctor-specialization';
import {Observable, of} from 'rxjs';
import {environment} from '../../../../environments/environment.dev';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DoctorSpecializationService {
  private url = `${environment.serverUrl}/doctor-specializations`;

  constructor(
    private formBuilder: FormBuilder,
    private httpClient: HttpClient
  ) { }

  public buildDoctorSpecialization(): FormGroup {
    return this.formBuilder.group({
      name: new FormControl(''),
      description: new FormControl(''),
      realizedDate: new FormControl('')
    })
  }

  create(pwzNumber:string, doctorSpecializationToCreate: DoctorSpecialization) : Observable<DoctorSpecialization[]>{
    const specializations = [doctorSpecializationToCreate];
    return this.httpClient.post<DoctorSpecialization[]>(
      `${this.url}/add`,
      specializations,
      {
        params: {
          pwzNumber: pwzNumber
        }
      }
    );
  }

  update(doctorSpecialization: DoctorSpecialization): Observable<DoctorSpecialization> {
    return this.httpClient.put<DoctorSpecialization>(
      `${this.url}`,
      doctorSpecialization
    );
  }

  delete(doctorSpecialization: DoctorSpecialization): Observable<DoctorSpecialization> {
    return of(doctorSpecialization);
  }

  getAllAvailableSpecializationNames() : Observable<string[]>{
    const doctorSpecializations = [
      "Cardiologist",
      "Neurologist",
      "Pediatrician",
      "Dermatologist",
      "Oncologist",
      "Psychiatrist",
      "Orthopedic Surgeon",
      "Gastroenterologist",
      "Endocrinologist",
      "Kardiologia"
    ];

    return this.httpClient.get<string[]>(
      `${this.url}/specialization-names`
    );
  }

  getDoctorSpecializations(pwzNumber: string): Observable<DoctorSpecialization[]> {
    return this.httpClient.get<DoctorSpecialization[]>(
      `${this.url}`,
      {
        params: {
          pwzNumber: pwzNumber
        }
      }
    );
  }
}
