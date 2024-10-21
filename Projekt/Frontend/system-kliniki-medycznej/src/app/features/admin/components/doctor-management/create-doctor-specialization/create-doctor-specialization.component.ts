import {ChangeDetectionStrategy, Component, Inject} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogContainer, MatDialogContent, MatDialogRef} from '@angular/material/dialog';
import {MatCardTitle} from '@angular/material/card';
import {MatFormField, MatFormFieldModule, MatLabel} from '@angular/material/form-field';
import {MatInput, MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {provideNativeDateAdapter} from '@angular/material/core';

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

  constructor(private dialogRef: MatDialogRef<CreateDoctorSpecializationComponent>, @Inject(MAT_DIALOG_DATA) public doctorSpecializationFormGroup: FormGroup) {
  }

  onSubmit(){
    this.dialogRef.close(
    {...this.doctorSpecializationFormGroup.value}
    );
  }
}
