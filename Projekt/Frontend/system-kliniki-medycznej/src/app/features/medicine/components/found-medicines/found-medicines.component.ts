import {Component, EventEmitter, Input, Output} from '@angular/core';
import {MedicineDto} from '../../model/medicine-dto';
import {PrescriptionMedicine} from '../../../prescriptions/model/prescription-medicine';
import {MatDialog} from '@angular/material/dialog';
import {MedicineDosageDialogComponent} from '../../dialogs/medicine-dosage-dialog/medicine-dosage-dialog.component';
import {HospitalizationInfo} from '../../../patient-card/model/hospitalization-info';
import {
  AddHospitalizationDialogComponent
} from '../../../patient-card/dialogs/add-hospitalization-dialog/add-hospitalization-dialog.component';
import {
  CreateNewHospitalizationDialogComponent
} from '../../../patient-card/dialogs/create-new-hospitalization-dialog/create-new-hospitalization-dialog.component';

@Component({
  selector: 'app-found-medicines',
  standalone: true,
  imports: [],
  templateUrl: './found-medicines.component.html',
  styleUrl: './found-medicines.component.css'
})
export class FoundMedicinesComponent {
  @Input() medicines: MedicineDto[] = [];
  @Input({required: true}) type!: "prescriptionMedicine" | "hospitalization" | "medicinePanel";
  @Output() medicineToAddEmitter = new EventEmitter<any>();

  constructor(
    private dialog: MatDialog
  ) {
  }

  addMedicine(medicine: MedicineDto) {
    if (this.type === 'prescriptionMedicine') {
      this.dialog.open(MedicineDosageDialogComponent, {
        data: medicine
      }).afterClosed().subscribe((prescriptionMedicine: PrescriptionMedicine) => {
        console.log(prescriptionMedicine);
        if (prescriptionMedicine) {
          this.medicineToAddEmitter.emit(prescriptionMedicine);
        }
      });
    } else if (this.type === 'hospitalization') {
      this.dialog.open(CreateNewHospitalizationDialogComponent, {
        data: medicine
      }).afterClosed().subscribe((prescriptionMedicine: { medicine: MedicineDto; dosage: string; notes: string, finishDate:string }) => {
        console.log(prescriptionMedicine);
        if (prescriptionMedicine) {
          this.medicineToAddEmitter.emit(prescriptionMedicine);
        }
      });
    }

  }
}
