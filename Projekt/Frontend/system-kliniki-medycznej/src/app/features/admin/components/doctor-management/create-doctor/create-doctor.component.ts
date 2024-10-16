import {ChangeDetectionStrategy, Component} from '@angular/core';
import {MatDialogContainer, MatDialogContent, MatDialogTitle} from '@angular/material/dialog';
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
    MatFormFieldModule, MatInputModule, MatDatepickerModule, MatDialogContent, MatDialogContainer,
  ],
  templateUrl: './create-doctor.component.html',
  styleUrl: './create-doctor.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CreateDoctorComponent {

}
