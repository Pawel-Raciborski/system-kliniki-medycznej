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
    const testData: PatientDiseaseHospitalizationInfo[] = [
      {
        id: 1,
        description: "Acute bronchitis",
        doctorFullName: "Dr. John Smith",
        detectionDate: "2023-10-01",
        cureStatus: "In Progress",
        finishCureDate: "",
        currentHospitalization: {
          id: 101,
          medicine: {
            id: 1001,
            specimenType: "Tablet",
            medicinalProductName: "Bronchicure",
            commonName: "Cough Suppressant",
            pharmaceuticalFormName: "Oral Tablet",
            medicinalProductPower: "10mg",
            activeSubstanceName: "Dextromethorphan",
            subjectMedicinalProductName: "Bronchicure 10mg Tablets",
            registryNumber: "BRX-2023-01",
            procedureTypeName: "Standard Registration",
            expirationDateString: "2025-12-31",
            atcCode: "R05DA09",
            targetSpecies: "Human",
          },
          cureDosage: "2 tablets daily after meals",
          notes: "Patient is responding well to treatment.",
          medicineUpdateDate: "2023-10-10",
        },
      },
      {
        id: 2,
        description: "Type 2 Diabetes Mellitus",
        doctorFullName: "Dr. Emma Johnson",
        detectionDate: "2022-03-15",
        cureStatus: "Stable",
        finishCureDate: "",
        currentHospitalization: {
          id: 102,
          medicine: {
            id: 1002,
            specimenType: "Injection",
            medicinalProductName: "Insulin Glargine",
            commonName: "Insulin",
            pharmaceuticalFormName: "Subcutaneous Injection",
            medicinalProductPower: "100 units/mL",
            activeSubstanceName: "Insulin Glargine",
            subjectMedicinalProductName: "Insulin Glargine Injection 100 units/mL",
            registryNumber: "INS-2021-45",
            procedureTypeName: "Biological Product",
            expirationDateString: "2024-09-30",
            atcCode: "A10AE04",
            targetSpecies: "Human",
          },
          cureDosage: "10 units before bedtime",
          notes: "Maintain blood glucose monitoring.",
          medicineUpdateDate: "2023-11-01",
        },
      },
      {
        id: 3,
        description: "Hypertension",
        doctorFullName: "Dr. William Brown",
        detectionDate: "2021-06-10",
        cureStatus: "Under Control",
        finishCureDate: "2023-08-15",
        currentHospitalization: {
          id: 103,
          medicine: {
            id: 1003,
            specimenType: "Capsule",
            medicinalProductName: "Amlodipine",
            commonName: "Calcium Channel Blocker",
            pharmaceuticalFormName: "Oral Capsule",
            medicinalProductPower: "5mg",
            activeSubstanceName: "Amlodipine Besylate",
            subjectMedicinalProductName: "Amlodipine 5mg Capsules",
            registryNumber: "AML-2020-78",
            procedureTypeName: "Standard Registration",
            expirationDateString: "2024-05-20",
            atcCode: "C08CA01",
            targetSpecies: "Human",
          },
          cureDosage: "1 capsule daily in the morning",
          notes: "Patient achieved target blood pressure.",
          medicineUpdateDate: "2023-07-01",
        },
      },
    ];

    return of(testData);
  }
}
