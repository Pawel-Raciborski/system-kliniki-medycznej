import {ChangeDetectionStrategy, Component} from '@angular/core';
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
import {FormArray, FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {DoctorSpecialization} from '../../../../doctor/domain/doctor-specialization';
import {DoctorSpecializationService} from '../../../../doctor-specialization/services/doctor-specialization.service';

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
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CreateDoctorComponent {
  doctorForm: FormGroup = new FormGroup<any>({
    registerAccountData: new FormGroup({
      username: new FormControl(''),
      password: new FormControl(''),
      passwordConfirmation: new FormControl(''),
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
    dateOfEmployment: new FormControl(this.getCurrentDate()),
    doctorSpecializations: new FormArray<FormGroup<{name: FormControl<string | null>, description: FormControl<string | null>, realizedDate: FormControl<string | null>}>>([])
  });

  private getCurrentDate() {
    return new Date().toLocaleDateString().replaceAll(".","-");
  }

  private createDoctorSpecializationFormGroup() : FormGroup{
    return new FormGroup({
      name : new FormControl(''),
      description: new FormControl(''),
      realizedDate : new FormControl(''),
    });
  }

  constructor(
    private dialog: MatDialog,
    private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<CreateDoctorComponent>,
    private specializationService: DoctorSpecializationService
    ) {
  }

  // TODO need to fix
  addSpecialization() {
    let addSpecializationForm = this.specializationService.buildDoctorSpecialization();

    let matDialogRef = this.dialog.open(CreateDoctorSpecializationComponent,{
      data: addSpecializationForm
    });

    matDialogRef.afterClosed().subscribe((data: DoctorSpecialization) => {
      if(data){
        const doctorSpecializations = this.doctorSpecializations;
        doctorSpecializations.push(addSpecializationForm);

        console.log(doctorSpecializations)
      }
    });
  }

  get doctorSpecializations(){
    return this.doctorForm.controls["doctorSpecializations"] as FormArray;
  }

  onFormSubmit() {
    this.dialogRef.close({
      doctorFormDto : this.doctorForm.value
    });
  }

  get pwzNumber(){
    return this.doctorForm.get('pwzNumber') as FormControl;
  }
}
