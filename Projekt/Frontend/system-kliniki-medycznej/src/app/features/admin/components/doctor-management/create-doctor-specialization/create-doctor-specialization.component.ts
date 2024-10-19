import {ChangeDetectionStrategy, Component} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatDialogContainer, MatDialogContent, MatDialogRef} from '@angular/material/dialog';
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
  doctorSpecializationFormGroup: FormGroup = new FormGroup({
    name : new FormControl('', Validators.required),
    description : new FormControl('', Validators.required),
    realizedDate : new FormControl('', Validators.required),
  });

  constructor(private dialogRef: MatDialogRef<CreateDoctorSpecializationComponent>) {
  }

  onSubmit(){
    this.dialogRef.close({
      data: this.doctorSpecializationFormGroup.value
    });
  }
}
