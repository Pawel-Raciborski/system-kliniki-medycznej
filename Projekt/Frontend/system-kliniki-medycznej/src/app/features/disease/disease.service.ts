import { Injectable } from '@angular/core';
import {DoctorSearchOptions} from '../doctor/components/search-bar/model/doctor-search-options';
import {Observable, of} from 'rxjs';
import {DiseaseDto} from './model/disease-dto';
import {Pagination} from '../pagination/model/pagination';
import {SearchDisease} from './model/search-disease';
import {environment} from '../../../environments/environment.dev';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DiseaseService {
  url : string = `${environment.serverUrl}/diseases`;
  diseases: DiseaseDto[] = [
    {
      id: 1,
      category: "Infectious Disease",
      icd11Code: "1A20",
      icd11Title: "Cholera due to Vibrio cholerae 01, biovar cholerae",
      icd10Code: "A00.0",
      icd10Title: "Cholera due to Vibrio cholerae 01, biovar cholerae"
    },
    {
      id: 2,
      category: "Respiratory Disease",
      icd11Code: "CA23.0",
      icd11Title: "Acute viral rhinosinusitis (common cold)",
      icd10Code: "J00",
      icd10Title: "Acute nasopharyngitis [common cold]"
    },
    {
      id: 3,
      category: "Cardiovascular Disease",
      icd11Code: "BA40.0",
      icd11Title: "Essential (primary) hypertension",
      icd10Code: "I10",
      icd10Title: "Essential (primary) hypertension"
    },
    {
      id: 4,
      category: "Mental Health",
      icd11Code: "6A80",
      icd11Title: "Depressive episode, mild",
      icd10Code: "F32.0",
      icd10Title: "Mild depressive episode"
    },
    {
      id: 5,
      category: "Endocrine Disorder",
      icd11Code: "5A10",
      icd11Title: "Type 1 diabetes mellitus",
      icd10Code: "E10",
      icd10Title: "Type 1 diabetes mellitus"
    },
    {
      id: 6,
      category: "Neurological Disorder",
      icd11Code: "8A00.0",
      icd11Title: "Epilepsy, generalized onset",
      icd10Code: "G40.3",
      icd10Title: "Generalized idiopathic epilepsy"
    },
    {
      id: 7,
      category: "Digestive Disease",
      icd11Code: "DD20.0",
      icd11Title: "Gastroesophageal reflux disease with esophagitis",
      icd10Code: "K21.0",
      icd10Title: "Gastro-esophageal reflux disease with esophagitis"
    },
    {
      id: 8,
      category: "Skin Condition",
      icd11Code: "EA90",
      icd11Title: "Psoriasis vulgaris",
      icd10Code: "L40.0",
      icd10Title: "Psoriasis vulgaris"
    },
    {
      id: 9,
      category: "Musculoskeletal Disorder",
      icd11Code: "FA10",
      icd11Title: "Rheumatoid arthritis",
      icd10Code: "M05.9",
      icd10Title: "Rheumatoid arthritis, unspecified"
    },
    {
      id: 10,
      category: "Cancer",
      icd11Code: "2C20.0",
      icd11Title: "Malignant neoplasm of breast",
      icd10Code: "C50.9",
      icd10Title: "Malignant neoplasm of breast, unspecified"
    }
  ];
  constructor(
    private httpClient: HttpClient
  ) { }

  public findPagedDiseases(searchDisease: SearchDisease, pagination: Pagination): Observable<DiseaseDto[]>{
    return this.httpClient.post<DiseaseDto[]>(
      `${this.url}`,
      searchDisease,
      {
        params: {
          page: pagination.page,
          pageSize: pagination.pageSize,
        }
      }
    );
  }
}
