import { Component } from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-create-patient-dialog',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './create-patient-dialog.component.html',
  styleUrl: './create-patient-dialog.component.css'
})
export class CreatePatientDialogComponent {
  form = new FormGroup({
    pesel : new FormControl(''),
    parentPesel : new FormControl(),
    name : new FormControl(''),
    surname : new FormControl(''),
    birthDate : new FormControl(''),
    gender : new FormControl(''),
    phoneNumber : new FormControl(''),
    address : new FormGroup({
      street : new FormControl(''),
      apartmentNumber : new FormControl(''),
      postalCode : new FormControl(''),
      city : new FormControl(''),
    })
  });

  constructor(
    private dialogRef: MatDialogRef<CreatePatientDialogComponent>
  ) {
  }

  cancel() {
    this.dialogRef.close();
  }

  addPatient() {
    this.dialogRef.close({...this.form.value});
  }
}
