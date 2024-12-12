import { Component } from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';

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
}
