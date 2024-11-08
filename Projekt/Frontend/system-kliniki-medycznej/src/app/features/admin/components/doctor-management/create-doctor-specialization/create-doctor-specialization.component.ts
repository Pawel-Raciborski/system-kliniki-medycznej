import {ChangeDetectionStrategy, Component, Inject} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogContainer, MatDialogContent, MatDialogRef} from '@angular/material/dialog';
import {MatCardTitle} from '@angular/material/card';
import {MatFormField, MatFormFieldModule, MatLabel} from '@angular/material/form-field';
import {MatInput, MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {provideNativeDateAdapter} from '@angular/material/core';
import {DoctorSpecialization} from '../../../../doctor/domain/doctor-specialization';

@Component({
  selector: 'app-create-doctor-specialization',
  standalone: true,
  providers: [
    provideNativeDateAdapter()
  ],
  imports: [
    MatDialogContent,
    MatCardTitle,
    MatFormField,
    MatInput,
    MatLabel,
    ReactiveFormsModule,
  ],
  templateUrl: './create-doctor-specialization.component.html',
  styleUrl: './create-doctor-specialization.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CreateDoctorSpecializationComponent {
  form:FormGroup = new FormGroup({
    name: new FormControl(''),
    description: new FormControl(''),
    realizedDate: new FormControl(''),
  });
  constructor(
    private dialogRef: MatDialogRef<CreateDoctorSpecializationComponent>,
    @Inject(MAT_DIALOG_DATA) public doctorSpecialization: DoctorSpecialization) {
    if(doctorSpecialization){
      this.form.patchValue(doctorSpecialization);
    }
  }

  onSubmit(){
    this.dialogRef.close(
    {...this.form.value}
    );
  }

  cancel() {
    this.dialogRef.close();
  }
}
