import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {DiseaseDto} from '../../../disease/model/disease-dto';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {
  AddHospitalizationDialogComponent
} from '../../../patient-card/dialogs/add-hospitalization-dialog/add-hospitalization-dialog.component';
import {MedicineDto} from '../../../medicine/model/medicine-dto';
import {DiseaseTableComponent} from '../../../disease/components/disease-table/disease-table.component';
import {MedicineTableComponent} from '../../../medicine/components/medicine-table/medicine-table.component';
import {FoundMedicinesComponent} from '../../../medicine/components/found-medicines/found-medicines.component';
import {PrescriptionMedicine} from '../../../prescriptions/model/prescription-medicine';
import {
  AddedMedicinesToPrescriptionComponent
} from '../../../medicine/components/added-medicines-to-prescription/added-medicines-to-prescription.component';

@Component({
  selector: 'app-create-patient-disease-description',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    DiseaseTableComponent,
    MedicineTableComponent,
    FoundMedicinesComponent,
    AddedMedicinesToPrescriptionComponent
  ],
  templateUrl: './create-patient-disease-description-dialog.component.html',
  styleUrl: './create-patient-disease-description-dialog.component.css'
})
export class CreatePatientDiseaseDescriptionDialogComponent {
  form = new FormGroup({
    description: new FormControl('')
  })
  hospitalization: {
    medicine: MedicineDto,
    dosage: string,
    notes: string,
    finishDate: string
  }[] = [];

  constructor(
    @Inject(MAT_DIALOG_DATA) public disease: DiseaseDto,
    private dialogRef: MatDialogRef<CreatePatientDiseaseDescriptionDialogComponent>,
    private dialog: MatDialog
  ) {
  }

  save() {
    this.dialogRef.close({
      disease: this.disease,
      description: this.form.value.description,
      hospitalizations: this.hospitalization
    });
  }

  openAddHospitalizationDialog() {
    this.dialog.open(AddHospitalizationDialogComponent).afterClosed().subscribe(data => {
      this.hospitalization.push(data);
    })
  }

  get getPrescriptionMedicines(): PrescriptionMedicine[] {
    return this.hospitalization.map(d => {
      return {
        medicine: d.medicine,
        dosage: d.dosage
      }
    })
  }
}
