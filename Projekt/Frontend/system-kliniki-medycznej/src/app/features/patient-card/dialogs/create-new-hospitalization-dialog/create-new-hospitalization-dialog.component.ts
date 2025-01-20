import {Component, Inject} from '@angular/core';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {MedicineDto} from '../../../medicine/model/medicine-dto';
import {PrescriptionMedicine} from '../../../prescriptions/model/prescription-medicine';
import {DateFormatterService} from '../../../../services/date-formatter.service';

@Component({
  selector: 'app-create-new-hospitalization-dialog',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './create-new-hospitalization-dialog.component.html',
  styleUrl: './create-new-hospitalization-dialog.component.css'
})
export class CreateNewHospitalizationDialogComponent {
  dosageForm: FormGroup = new FormGroup({
    dosage: new FormControl(''),
    notes: new FormControl(''),
    finishDate: new FormControl('')
  });
  constructor(
    @Inject(MAT_DIALOG_DATA) public medicine: MedicineDto,
    private dialogRef: MatDialogRef<CreateNewHospitalizationDialogComponent>
  ) {
  }

  save(medicine: MedicineDto) {
    let dosageForm: any = this.dosageForm.value;
    let prescriptionMedicine = {
      medicine: medicine,
      dosage: dosageForm.dosage,
      notes: dosageForm.notes,
      finishDate: dosageForm.finishDate
    };

    this.dialogRef.close(prescriptionMedicine);
  }

  close() {
    this.dialogRef.close();
  }

}
