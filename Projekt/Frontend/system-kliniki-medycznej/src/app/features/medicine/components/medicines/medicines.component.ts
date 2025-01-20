import {Component, OnInit} from '@angular/core';
import {MedicineDto} from '../../model/medicine-dto';
import {Pagination} from '../../../pagination/model/pagination';
import {MedicineService} from '../../services/medicine.service';
import {MatDialogRef} from '@angular/material/dialog';
import {SearchMedicineData} from '../../../prescriptions/model/search-medicine-data';
import {FoundMedicinesComponent} from '../found-medicines/found-medicines.component';
import {
  SearchMedicineBarComponent
} from '../../../prescriptions/components/search-medicine-bar/search-medicine-bar.component';
import {MedicineTableComponent} from '../medicine-table/medicine-table.component';

@Component({
  selector: 'app-medicines',
  standalone: true,
  imports: [
    FoundMedicinesComponent,
    SearchMedicineBarComponent,
    MedicineTableComponent
  ],
  templateUrl: './medicines.component.html',
  styleUrl: './medicines.component.css'
})
export class MedicinesComponent implements OnInit{
  medicines: MedicineDto[] = [];
  pagination: Pagination = {
    page: 0,
    pageSize: 20
  };

  constructor(
    private medicineService: MedicineService,
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
}
