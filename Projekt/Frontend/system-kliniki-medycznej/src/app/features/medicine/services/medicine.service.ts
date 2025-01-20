import { Injectable } from '@angular/core';
import {SearchMedicineData} from '../../prescriptions/model/search-medicine-data';
import {Pagination} from '../../pagination/model/pagination';
import {Observable, of} from 'rxjs';
import {MedicineDto} from '../model/medicine-dto';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../../environments/environment.dev';

@Injectable({
  providedIn: 'root'
})
export class MedicineService {
  url = `${environment.serverUrl}/medicine`
  constructor(
    private httpClient: HttpClient
  ) { }

  searchMedicine(searchMedicineData: SearchMedicineData, pagination: Pagination): Observable<MedicineDto[]> {
    const medicines: MedicineDto[] = [
      {
        id: 12121,
        specimenType: "Ludzki",
        medicinalProductName: "0,9% Sodium Chloride-BRAUN",
        commonName: "Natrii chloridum",
        pharmaceuticalFormName: "Roztwór do infuzji",
        medicinalProductPower: "9 mg/ml",
        activeSubstanceName: "Natrii chloridum",
        subjectMedicinalProductName: "B. Braun Melsungen AG",
        registryNumber: "08754",
        procedureTypeName: "NAR",
        expirationDateString: "Bezterminowe",
        atcCode: "B05CB01",
        targetSpecies: ""
      },
      {
        id: 1111,
        specimenType: "Ludzki",
        medicinalProductName: "0,9% Sodium Chloride-BRAUN",
        commonName: "Natrii chloridum",
        pharmaceuticalFormName: "Roztwór do infuzji",
        medicinalProductPower: "9 mg/ml",
        activeSubstanceName: "Natrii chloridum",
        subjectMedicinalProductName: "B. Braun Melsungen AG",
        registryNumber: "08754",
        procedureTypeName: "NAR",
        expirationDateString: "Bezterminowe",
        atcCode: "B05CB01",
        targetSpecies: ""
      },
      {
        id: 10174,
        specimenType: "Ludzki",
        medicinalProductName: "0,9% Sodium Chloride-BRAUN",
        commonName: "Natrii chloridum",
        pharmaceuticalFormName: "Roztwór do infuzji",
        medicinalProductPower: "9 mg/ml",
        activeSubstanceName: "Natrii chloridum",
        subjectMedicinalProductName: "B. Braun Melsungen AG",
        registryNumber: "08754",
        procedureTypeName: "NAR",
        expirationDateString: "Bezterminowe",
        atcCode: "B05CB01",
        targetSpecies: ""
      },
      {
        id: 6365,
        specimenType: "Ludzki",
        medicinalProductName: "1% Spirytusowy roztwór fioletu gencjanowego Gemi",
        commonName: "Methylrosanilinii chloridum",
        pharmaceuticalFormName: "Płyn na skórę",
        medicinalProductPower: "10 mg/g",
        activeSubstanceName: "Methylrosanilinii chloridum",
        subjectMedicinalProductName: "Grzegorz Nowakowski Przedsiębiorstwo Produkcji Farmaceutycznej \"GEMI\"",
        registryNumber: "03167",
        procedureTypeName: "NAR",
        expirationDateString: "Bezterminowe",
        atcCode: "",
        targetSpecies: ""
      },
      {
        id: 7194,
        specimenType: "Ludzki",
        medicinalProductName: "1% Wodny roztwór fioletu gencjanowego Gemi",
        commonName: "Methylrosanilinii chloridum",
        pharmaceuticalFormName: "Płyn na skórę",
        medicinalProductPower: "10 mg/g",
        activeSubstanceName: "Methylrosanilinii chloridum",
        subjectMedicinalProductName: "Grzegorz Nowakowski Przedsiębiorstwo Produkcji Farmaceutycznej \"GEMI\"",
        registryNumber: "03169",
        procedureTypeName: "NAR",
        expirationDateString: "Bezterminowe",
        atcCode: "",
        targetSpecies: ""
      }
    ];
    return this.httpClient.post<MedicineDto[]>(
      `${this.url}/search`,
      searchMedicineData,
      {
        params: {
          page: pagination.page,
          pageSize: pagination.pageSize,
        }
      }
    );
  }
}
