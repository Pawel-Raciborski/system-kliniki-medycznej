import { Injectable } from '@angular/core';
import {Pagination} from '../../pagination/model/pagination';
import {Observable, of} from 'rxjs';
import {PatientDiseaseHospitalizationInfo} from '../../patient-card/model/patient-disease-hospitalization-info';
import {HospitalizationInfo} from '../../patient-card/model/hospitalization-info';
import {SearchDisease} from '../../disease/model/search-disease';
import {UpdatePatientDiseaseHospitalization} from '../../patient-card/model/update-patient-disease-hospitalization';
import {MedicineDto} from '../../medicine/model/medicine-dto';
import {CreateHospitalizationRequest} from '../../patient-card/model/create-hospitalization-request';
import {environment} from '../../../../environments/environment.dev';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HospitalizationService {
  url = `${environment.serverUrl}/patient-diseases`;
  hospitalizationUrl = `${environment.serverUrl}/hospitalization`;
  testData: PatientDiseaseHospitalizationInfo[] = [
    {
      diseaseId: 1,
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
        finishDate: "2024-12-30"
      },
      diseaseInfo: {
        id: 1,
        code: '1B70.Y',
        title: 'Bakteryjne zapalenie tkanki łącznej lub naczyń chłonnych wywołane przez inną określoną bakterię'
      }
    },
    {
      diseaseId: 2,
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
        finishDate: "2024-11-30"
      },
      diseaseInfo: {
        id: 2,
        code: '1B74',
        title: 'Powierzchowne bakteryjne zapalenie mieszków włosowych'
      }
    },
    {
      diseaseId: 3,
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
        finishDate: "2024-11-30"
      },
      diseaseInfo: {
        id: 3,
        code: '1B74.0',
        title: 'Powierzchowne zapalenie mieszków włosowych wywołane przez Staphylococcus aureus',
      }
    },
  ];
  constructor(
    private httpClient: HttpClient
  ) { }

  getPatientDiseaseHospitalizations(patientCardId: string, pagination: Pagination): Observable<PatientDiseaseHospitalizationInfo[]> {
    return this.httpClient.get<PatientDiseaseHospitalizationInfo[]>(
      `${this.url}`,
      {
        params: {
          patientCardId: patientCardId,
          page: pagination.page,
          pageSize: pagination.pageSize
        }
      }
    );
  }

  getHospitalizationHistory(patientDiseaseId: number, pagination: Pagination): Observable<HospitalizationInfo[]> {
    console.log('getHospitalizationHistory pagination', pagination);
    const hospitalizationInfoArray: HospitalizationInfo[] = [
      {
        id: 1,
        medicine: {
          id: 101,
          specimenType: "Tablet",
          medicinalProductName: "Paracetamol",
          commonName: "Paracetamol",
          pharmaceuticalFormName: "Tablet",
          medicinalProductPower: "500mg",
          activeSubstanceName: "Paracetamol",
          subjectMedicinalProductName: "Tylenol",
          registryNumber: "A12345",
          procedureTypeName: "Oral",
          expirationDateString: "2025-01-01",
          atcCode: "N02BE01",
          targetSpecies: "Human"
        },
        cureDosage: "1 tablet every 6 hours",
        notes: "Take with food",
        medicineUpdateDate: "2024-11-01",
        finishDate: "2024-12-30"
      },
      {
        id: 2,
        medicine: {
          id: 102,
          specimenType: "Injection",
          medicinalProductName: "Insulin",
          commonName: "Insulin",
          pharmaceuticalFormName: "Injection",
          medicinalProductPower: "100IU/ml",
          activeSubstanceName: "Insulin Human",
          subjectMedicinalProductName: "Lantus",
          registryNumber: "B67890",
          procedureTypeName: "Subcutaneous",
          expirationDateString: "2026-06-30",
          atcCode: "A10AE04",
          targetSpecies: "Human"
        },
        cureDosage: "10 IU before meals",
        notes: "Store in refrigerator",
        medicineUpdateDate: "2024-10-15",
        finishDate: "2024-11-30"
      },
      {
        id: 3,
        medicine: {
          id: 103,
          specimenType: "Capsule",
          medicinalProductName: "Amoxicillin",
          commonName: "Amoxicillin",
          pharmaceuticalFormName: "Capsule",
          medicinalProductPower: "250mg",
          activeSubstanceName: "Amoxicillin",
          subjectMedicinalProductName: "Amoxil",
          registryNumber: "C34567",
          procedureTypeName: "Oral",
          expirationDateString: "2024-12-31",
          atcCode: "J01CA04",
          targetSpecies: "Human"
        },
        cureDosage: "1 capsule every 8 hours",
        notes: "Complete the full course",
        medicineUpdateDate: "2024-09-20",
        finishDate: "2024-11-30"
      },
      {
        id: 4,
        medicine: {
          id: 104,
          specimenType: "Syrup",
          medicinalProductName: "Ibuprofen",
          commonName: "Ibuprofen",
          pharmaceuticalFormName: "Syrup",
          medicinalProductPower: "100mg/5ml",
          activeSubstanceName: "Ibuprofen",
          subjectMedicinalProductName: "Advil",
          registryNumber: "D98765",
          procedureTypeName: "Oral",
          expirationDateString: "2025-07-15",
          atcCode: "M01AE01",
          targetSpecies: "Human"
        },
        cureDosage: "10ml every 6 hours",
        notes: "Shake well before use",
        medicineUpdateDate: "2024-11-10",
        finishDate: "2024-11-30"
      },
      {
        id: 5,
        medicine: {
          id: 105,
          specimenType: "Cream",
          medicinalProductName: "Hydrocortisone",
          commonName: "Hydrocortisone",
          pharmaceuticalFormName: "Cream",
          medicinalProductPower: "1%",
          activeSubstanceName: "Hydrocortisone",
          subjectMedicinalProductName: "Cortizone-10",
          registryNumber: "E54321",
          procedureTypeName: "Topical",
          expirationDateString: "2024-08-01",
          atcCode: "D07AA02",
          targetSpecies: "Human"
        },
        cureDosage: "Apply thin layer twice daily",
        notes: "Do not use on broken skin",
        medicineUpdateDate: "2024-06-25",
        finishDate: "2024-11-30"
      },
      {
        id: 6,
        medicine: {
          id: 106,
          specimenType: "Tablet",
          medicinalProductName: "Metformin",
          commonName: "Metformin",
          pharmaceuticalFormName: "Tablet",
          medicinalProductPower: "500mg",
          activeSubstanceName: "Metformin Hydrochloride",
          subjectMedicinalProductName: "Glucophage",
          registryNumber: "F67890",
          procedureTypeName: "Oral",
          expirationDateString: "2026-03-20",
          atcCode: "A10BA02",
          targetSpecies: "Human"
        },
        cureDosage: "1 tablet twice daily with meals",
        notes: "Monitor blood sugar regularly",
        medicineUpdateDate: "2024-10-01",
        finishDate: "2024-11-30"
      },
      {
        id: 7,
        medicine: {
          id: 107,
          specimenType: "Solution",
          medicinalProductName: "Saline",
          commonName: "Normal Saline",
          pharmaceuticalFormName: "Solution",
          medicinalProductPower: "0.9%",
          activeSubstanceName: "Sodium Chloride",
          subjectMedicinalProductName: "IV Saline",
          registryNumber: "G11223",
          procedureTypeName: "Intravenous",
          expirationDateString: "2025-05-01",
          atcCode: "B05BB01",
          targetSpecies: "Human"
        },
        cureDosage: "500ml IV as needed",
        notes: "Administer slowly",
        medicineUpdateDate: "2024-11-05",
        finishDate: "2024-11-30"
      },
      {
        id: 8,
        medicine: {
          id: 108,
          specimenType: "Tablet",
          medicinalProductName: "Aspirin",
          commonName: "Aspirin",
          pharmaceuticalFormName: "Tablet",
          medicinalProductPower: "100mg",
          activeSubstanceName: "Acetylsalicylic Acid",
          subjectMedicinalProductName: "Aspirin Cardio",
          registryNumber: "H33445",
          procedureTypeName: "Oral",
          expirationDateString: "2025-10-10",
          atcCode: "B01AC06",
          targetSpecies: "Human"
        },
        cureDosage: "1 tablet daily",
        notes: "Take with water",
        medicineUpdateDate: "2024-08-30",
        finishDate: "2024-11-30"
      },
      {
        id: 9,
        medicine: {
          id: 109,
          specimenType: "Drop",
          medicinalProductName: "Timolol",
          commonName: "Timolol",
          pharmaceuticalFormName: "Eye Drops",
          medicinalProductPower: "0.5%",
          activeSubstanceName: "Timolol Maleate",
          subjectMedicinalProductName: "Timoptic",
          registryNumber: "I55443",
          procedureTypeName: "Ophthalmic",
          expirationDateString: "2025-12-01",
          atcCode: "S01ED01",
          targetSpecies: "Human"
        },
        cureDosage: "1 drop in each eye twice daily",
        notes: "Do not touch dropper tip to eye",
        medicineUpdateDate: "2024-07-15",
        finishDate: "2024-11-30"
      },
      {
        id: 10,
        medicine: {
          id: 110,
          specimenType: "Inhaler",
          medicinalProductName: "Salbutamol",
          commonName: "Salbutamol",
          pharmaceuticalFormName: "Inhaler",
          medicinalProductPower: "100mcg/dose",
          activeSubstanceName: "Salbutamol",
          subjectMedicinalProductName: "Ventolin",
          registryNumber: "J66789",
          procedureTypeName: "Inhalation",
          expirationDateString: "2024-11-30",
          atcCode: "R03AC02",
          targetSpecies: "Human"
        },
        cureDosage: "2 puffs every 4 hours as needed",
        notes: "Shake well before use",
        medicineUpdateDate: "2024-11-15",
        finishDate: "2024-11-30"
      }
    ];
    let {page, pageSize} = {...pagination};
    return this.httpClient.get<HospitalizationInfo[]>(
      `${this.url}/${patientDiseaseId}/hospitalization-history`,
      {
        params: {
          page: pagination.page,
          pageSize: pagination.pageSize,
        }
      }
    );
  }

  updateDiseaseHospitalization(hospitalizationInfo: HospitalizationInfo): Observable<HospitalizationInfo> {
    return this.httpClient.put<HospitalizationInfo>(
      `${this.hospitalizationUrl}/update`,
      hospitalizationInfo
    );
  }

  searchSpecifiedPatientDiseaseHospitalizations(patientCardId: string, searchDisease: SearchDisease, pagination: Pagination) {
    return this.httpClient.post<PatientDiseaseHospitalizationInfo[]>(
      `${this.url}/search`,
      searchDisease,
      {
        params: {
          page: pagination.page,
          pageSize: pagination.pageSize,
          patientCardId: patientCardId
        }
      }
    )
  }

  buildHospitalization(hospitalizationToUpdate: HospitalizationInfo, diseaseId: number) : UpdatePatientDiseaseHospitalization {
    return {
      hospitalization: hospitalizationToUpdate,
      patientDiseaseId: diseaseId
    }
  }

  buildHospitalizationRequest(data: {
    medicine: MedicineDto;
    dosage: string;
    notes: string,
    finishDate: string
  }, patientDiseaseId: number): CreateHospitalizationRequest {
    return {
      patientDiseaseId: patientDiseaseId,
      medicine: data.medicine,
      dosage: data.dosage,
      notes: data.notes,
      finishDate: data.finishDate
    }
  }

  create(hospitalizationRequest: CreateHospitalizationRequest) {
    console.log(hospitalizationRequest);
    let hospitalization : HospitalizationInfo = {
      id: 2138,
      medicine: {
        id: 110,
        specimenType: "Inhaler",
        medicinalProductName: "Salbutamol",
        commonName: "Salbutamol",
        pharmaceuticalFormName: "Inhaler",
        medicinalProductPower: "100mcg/dose",
        activeSubstanceName: "Salbutamol",
        subjectMedicinalProductName: "Ventolin",
        registryNumber: "J66789",
        procedureTypeName: "Inhalation",
        expirationDateString: "2024-11-30",
        atcCode: "JP220100",
        targetSpecies: "Human"
      },
      cureDosage: "2 puffs every 4 hours as needed",
      notes: "Shake well before use",
      medicineUpdateDate: "2024-11-15",
      finishDate: hospitalizationRequest.finishDate
    };

    return this.httpClient.post<HospitalizationInfo>(
      `${this.url}/create`,
      hospitalizationRequest
    );
  }
}
