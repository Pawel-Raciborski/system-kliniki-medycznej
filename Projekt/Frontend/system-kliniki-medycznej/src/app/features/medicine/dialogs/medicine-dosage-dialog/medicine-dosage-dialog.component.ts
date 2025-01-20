import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogContent, MatDialogRef} from '@angular/material/dialog';
import {MedicineDto} from '../../model/medicine-dto';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {PrescriptionMedicine} from '../../../prescriptions/model/prescription-medicine';

@Component({
  selector: 'app-medicine-dosage-dialog',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatDialogContent
  ],
  templateUrl: './medicine-dosage-dialog.component.html',
  styleUrl: './medicine-dosage-dialog.component.css'
})
export class MedicineDosageDialogComponent {
  dosageForm: FormGroup = new FormGroup({
    dosage: new FormControl('')
  });
  constructor(
    @Inject(MAT_DIALOG_DATA) public medicine: MedicineDto,
    private dialogRef: MatDialogRef<MedicineDosageDialogComponent>
  ) {
  }

  save(medicine: MedicineDto) {
    let dosageForm: any = this.dosageForm.value;
    let prescriptionMedicine: PrescriptionMedicine = {
      medicine: medicine,
      dosage: dosageForm.dosage
    };

    this.dialogRef.close(prescriptionMedicine);
  }

  close() {
    this.dialogRef.close();
  }
}
