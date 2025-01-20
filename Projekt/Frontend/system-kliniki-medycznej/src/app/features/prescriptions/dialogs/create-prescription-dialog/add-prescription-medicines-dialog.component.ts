import {Component, OnInit} from '@angular/core';
import {SearchMedicineBarComponent} from '../../components/search-medicine-bar/search-medicine-bar.component';
import {SearchMedicineData} from '../../model/search-medicine-data';
import {MedicineDto} from '../../../medicine/model/medicine-dto';
import {MedicineService} from '../../../medicine/services/medicine.service';
import {Pagination} from '../../../pagination/model/pagination';
import {FoundMedicinesComponent} from '../../../medicine/components/found-medicines/found-medicines.component';
import {
  AddedMedicinesToPrescriptionComponent
} from '../../../medicine/components/added-medicines-to-prescription/added-medicines-to-prescription.component';
import {MatDialogContent, MatDialogRef} from '@angular/material/dialog';
import {PrescriptionMedicine} from '../../model/prescription-medicine';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-create-prescription-dialog',
  standalone: true,
  imports: [
    SearchMedicineBarComponent,
    FoundMedicinesComponent,
    AddedMedicinesToPrescriptionComponent,
    MatDialogContent,
    ReactiveFormsModule
  ],
  templateUrl: './add-prescription-medicines-dialog.component.html',
  styleUrl: './add-prescription-medicines-dialog.component.css'
})
export class AddPrescriptionMedicinesDialogComponent implements OnInit {
  prescriptionForm = new FormGroup({
    expirationDate: new FormControl(''),
    description: new FormControl(''),
    patientPesel: new FormControl('')
  });
  medicines: MedicineDto[] = [];
  medicinesOnPrescription: PrescriptionMedicine[] = [];
  pagination: Pagination = {
    page: 0,
    pageSize: 20
  };

  constructor(
    private medicineService: MedicineService,
    private dialogRef: MatDialogRef<AddPrescriptionMedicinesDialogComponent>
  ) {
  }

  ngOnInit(): void {
    this.medicineService.searchMedicine({medicinalProductName: ''}, this.pagination).subscribe(data => {
      this.medicines = data;
    });
  }

  searchMedicine(searchMedicineData: SearchMedicineData) {
    this.clearPagination();
    this.medicineService.searchMedicine(searchMedicineData, this.pagination).subscribe(data => {
      this.medicines = data;
    });
  }

  private clearPagination() {
    this.pagination.page = 0;
  }

  addMedicineToList(medicine: PrescriptionMedicine) {
    this.medicines = this.medicines.filter(m => m.id !== medicine.medicine.id);
    this.medicinesOnPrescription.push(medicine);
  }

  removeMedicine(prescriptionMedicine: PrescriptionMedicine) {
    this.medicinesOnPrescription = this.medicinesOnPrescription.filter(p => p !== prescriptionMedicine);
    let medicine = prescriptionMedicine.medicine;
    this.medicines.push(medicine);
    console.log(this.medicines, this.medicinesOnPrescription);
  }


  savePrescription() {
    let prescriptionFormValues = this.prescriptionForm.value;

    this.dialogRef.close({
        expirationDate: prescriptionFormValues.expirationDate,
        description: prescriptionFormValues.description,
        prescriptionMedicines: this.medicinesOnPrescription,
        patientPesel: prescriptionFormValues.patientPesel,
      }
    );
  }

  cancel() {
    this.dialogRef.close();
  }
}
