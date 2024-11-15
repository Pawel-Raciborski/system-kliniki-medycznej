import { Injectable } from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {DoctorSpecialization} from '../../doctor/domain/doctor-specialization';
import {Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DoctorSpecializationService {

  constructor(
    private formBuilder: FormBuilder
  ) { }

  public buildDoctorSpecialization(): FormGroup {
    return this.formBuilder.group({
      name: new FormControl(''),
      description: new FormControl(''),
      realizedDate: new FormControl('')
    })
  }

  create(doctorSpecializationToCreate: DoctorSpecialization) : Observable<DoctorSpecialization>{
    return of(doctorSpecializationToCreate);
  }

  update(doctorSpecialization: DoctorSpecialization): Observable<DoctorSpecialization> {
    return of(doctorSpecialization);
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
      "Ophthalmologist"
    ];

    return of(doctorSpecializations);
  }
}
