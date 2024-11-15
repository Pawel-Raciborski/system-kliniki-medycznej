import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {LocalStorageService} from '../../auth/services/local-storage.service';
import {HttpClient} from '@angular/common/http';
import {Pagination} from '../../pagination/model/pagination';
import {CardSummary} from '../model/card-summary';
import {PatientDiseaseHospitalizationInfo} from '../model/patient-disease-hospitalization-info';

@Injectable({
  providedIn: 'root'
})
export class PatientCardService {

  constructor(
    private localStorageService: LocalStorageService,
    private httpClient: HttpClient
  ) { }

  findPatientCard() :Observable<any>{
    let patientId: number = this.localStorageService.getKeyValue("patientId");
    console.log(patientId);
    return of();
  }

  getCardSummary(patientCardId: string) : Observable<CardSummary>{
    console.log(patientCardId);
    return of();
  }

  getHospitalizationHistory(patientCardId: string, pagination: Pagination) :Observable<PatientDiseaseHospitalizationInfo[]>{
    return of()
  }
}
