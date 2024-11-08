import {Component} from '@angular/core';
import {MatDialog, MatDialogContainer, MatDialogContent, MatDialogRef, MatDialogTitle} from '@angular/material/dialog';
import {MatFormField, MatFormFieldModule, MatHint, MatLabel} from '@angular/material/form-field';
import {MatInput, MatInputModule} from '@angular/material/input';
import {MatCardTitle} from '@angular/material/card';
import {MatRadioButton, MatRadioGroup} from '@angular/material/radio';
import {
  MatDatepicker,
  MatDatepickerInput,
  MatDatepickerModule,
  MatDatepickerToggle
} from '@angular/material/datepicker';
import {provideNativeDateAdapter} from '@angular/material/core';
import {
  CreateDoctorSpecializationComponent
} from '../create-doctor-specialization/create-doctor-specialization.component';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {DoctorSpecialization} from '../../../../doctor/domain/doctor-specialization';

@Component({
  selector: 'app-create-doctor',
  standalone: true,
  providers: [
    provideNativeDateAdapter()
  ],
  imports: [
    MatDialogTitle,
    MatFormField,
    MatInput,
    MatCardTitle,
    MatLabel,
    MatRadioGroup,
    MatRadioButton,
    MatDatepickerInput,
    MatDatepickerToggle,
    MatDatepicker,
    MatHint,
    MatFormFieldModule, MatInputModule, MatDatepickerModule, MatDialogContent, MatDialogContainer, ReactiveFormsModule,
  ],
  templateUrl: './create-doctor.component.html',
  styleUrl: './create-doctor.component.css',
})
export class CreateDoctorComponent {
  doctorForm: FormGroup = new FormGroup<any>({
    registerAccountData: new FormGroup({
      username: new FormControl(''),
      password: new FormControl(''),
      // passwordConfirmation: new FormControl(''),
      email: new FormControl(''),
    }),
    personalDetails: new FormGroup({
      pesel: new FormControl(''),
      name: new FormControl(''),
      surname: new FormControl(''),
      birthDate: new FormControl(''),
      gender: new FormControl(''),
      phoneNumber: new FormControl(''),
      address: new FormGroup({
        street: new FormControl(''),
        apartmentNumber: new FormControl(''),
        postalCode: new FormControl(''),
        city: new FormControl('')
      })
    }),
    pwzNumber: new FormControl(''),
    description: new FormControl(''),
    dateOfEmployment: new FormControl('')
  });
  doctorSpecializations: DoctorSpecialization[] = [];
  profileImage: File | null = null;


  constructor(
    private dialog: MatDialog,
    private dialogRef: MatDialogRef<CreateDoctorComponent>,
  ) {
  }

  addSpecialization() {
    this.dialog.open(CreateDoctorSpecializationComponent)
      .afterClosed()
      .subscribe((data: DoctorSpecialization) => {
        if (data) {
          console.log('Tworzenie specjalizacji', data);
          this.doctorSpecializations.push(data);
          console.log(this.doctorSpecializations);
        }
      });
  }

  onFormSubmit() {
    this.doctorForm.addControl('doctorSpecializations', new FormControl(this.doctorSpecializations));
    this.doctorForm.addControl('profileImage', new FormControl(this.profileImage));
    this.doctorForm.removeControl('registerAccountData.passwordConfirmation');

    this.dialogRef.close({...this.doctorForm.value});
  }

  get pwzNumber() {
    return this.doctorForm.get('pwzNumber') as FormControl;
  }

  get dateOfEmployment(){
    return this.doctorForm.get('dateOfEmployment') as FormControl;
  }

  showSpecializationDetails(doctorSpecialization: DoctorSpecialization) {
    this.dialog.open(CreateDoctorSpecializationComponent, {data: doctorSpecialization}).afterClosed()
      .subscribe((editedData: DoctorSpecialization) => {
        if(editedData){
          this.updateElement(doctorSpecialization, editedData);
        }
      });
  }

  private updateElement(doctorSpecialization: DoctorSpecialization, editedData: DoctorSpecialization) {
    let index = this.doctorSpecializations.findIndex(d => d === doctorSpecialization);

    if(index !== -1){
      this.doctorSpecializations[index] = editedData;
    }
  }

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;

    if(input.files && input.files.length > 0){
      this.profileImage = input.files[0];
    }
  }

  formatZipCode(event: KeyboardEvent) {
    const zipCodeInput = event.target as HTMLInputElement;
    let value = zipCodeInput.value.replace(/\D/g, '');

    if(value.length > 2){
      value = value.slice(0,2) + '-' + value.slice(2);
    }

    zipCodeInput.value = value;
  }

  cancel() {
    this.dialogRef.close();
  }

}
