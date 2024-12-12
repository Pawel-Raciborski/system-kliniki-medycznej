import { Component } from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MatDialogRef} from '@angular/material/dialog';
import {
  CreatePatientDialogComponent
} from '../../../patient/dialogs/create-patient-dialog/create-patient-dialog.component';
import {CreateAccountRequest} from '../../model/create-account-request';
import {Account} from '../../model/account';

@Component({
  selector: 'app-create-patient-account-dialog',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './create-patient-account-dialog.component.html',
  styleUrl: './create-patient-account-dialog.component.css'
})
export class CreatePatientAccountDialogComponent {
  form: FormGroup = new FormGroup({
    pesel: new FormControl(''),
    username: new FormControl(''),
    password: new FormControl(''),
    email: new FormControl(''),
  });

  constructor(
    private dialogRef: MatDialogRef<CreatePatientDialogComponent>
  ) {}

  onCreateAccountSubmit(){
    const {pesel, ...account} = this.form.value;
    const accountToCreate : CreateAccountRequest = {
      pesel: pesel,
      accountCredentials: account
    };

    this.dialogRef.close(accountToCreate);
  }

  cancel() {
    this.dialogRef.close();
  }
}
