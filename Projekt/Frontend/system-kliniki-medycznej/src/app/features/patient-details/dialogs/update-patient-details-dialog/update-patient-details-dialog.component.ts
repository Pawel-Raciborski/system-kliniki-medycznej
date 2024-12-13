import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {PatientDetails} from '../../../patient/model/patient-details';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-update-patient-details-dialog',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './update-patient-details-dialog.component.html',
  styleUrl: './update-patient-details-dialog.component.css'
})
export class UpdatePatientDetailsDialogComponent {
  form!: FormGroup;
  constructor(
    @Inject(MAT_DIALOG_DATA) private patientDetails: PatientDetails,
    private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<UpdatePatientDetailsDialogComponent>
  ) {
    console.log(patientDetails);
    this.buildForm();
  }

  private buildForm() {
    this.form = this.formBuilder.group({
      heightInCm: new FormControl(this.patientDetails.heightInCm),
      weightInKg: new FormControl(this.patientDetails.weightInKg),
      bloodType: new FormControl(this.patientDetails.bloodType),
    });
  }

  update() {
    this.patientDetails.weightInKg = this.form.value.weightInKg;
    this.patientDetails.heightInCm = this.form.value.heightInCm;
    this.patientDetails.bloodType = this.form.value.bloodType;

    this.dialogRef.close(this.patientDetails);
  }

  cancel() {
    this.dialogRef.close();
  }
}
