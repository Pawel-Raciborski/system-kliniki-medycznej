import {Component, OnInit, Output} from '@angular/core';
import {SearchDiseaseComponent} from '../../../disease/components/search-disease/search-disease.component';
import {SearchDisease} from '../../../disease/model/search-disease';
import {FoundMedicinesComponent} from '../../../medicine/components/found-medicines/found-medicines.component';
import {
  SearchMedicineBarComponent
} from '../../../prescriptions/components/search-medicine-bar/search-medicine-bar.component';
import {SearchMedicineData} from '../../../prescriptions/model/search-medicine-data';
import {MedicineDto} from '../../../medicine/model/medicine-dto';
import {MedicineService} from '../../../medicine/services/medicine.service';
import {Pagination} from '../../../pagination/model/pagination';
import {HospitalizationInfo} from '../../model/hospitalization-info';
import {CreateHospitalizationRequest} from '../../model/create-hospitalization-request';
import {MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-add-hospitalization-dialog',
  standalone: true,
  imports: [
    SearchDiseaseComponent,
    FoundMedicinesComponent,
    SearchMedicineBarComponent
  ],
  templateUrl: './add-hospitalization-dialog.component.html',
  styleUrl: './add-hospitalization-dialog.component.css'
})
export class AddHospitalizationDialogComponent implements OnInit{
  medicines: MedicineDto[] = [];
  pagination: Pagination = {
    page: 0,
    pageSize: 20
  };

  constructor(
    private medicineService: MedicineService,
    private dialogRef: MatDialogRef<AddHospitalizationDialogComponent>
  ) {
  }
  ngOnInit(): void {
    this.medicineService.searchMedicine({medicinalProductName: ''}, this.pagination).subscribe(data => {
      this.medicines = data;
    });
  }

  onMedicineSearch(searchMedicineData: SearchMedicineData) {
    this.clearPagination();
    this.medicineService.searchMedicine(searchMedicineData, this.pagination).subscribe(data => {
      this.medicines = data;
    });
  }

  private clearPagination() {
    this.pagination.page = 0;
  }

  createNewHospitalization(newHospitalization: { medicine: MedicineDto; dosage: string; notes: string, finishDate: string }) {
    this.dialogRef.close(newHospitalization);
  }

  close() {
    this.dialogRef.close();
  }
}
