import {Component, Input, OnInit, signal, WritableSignal} from '@angular/core';
import {PersonalDetails} from '../../domain/personal-details';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';
import {FormGeneratorService} from '../../../../services/form-generator.service';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {PersonalDetailsService} from '../../services/personal-details.service';

@Component({
  selector: 'app-personal-details',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormField,
    MatInput,
    MatLabel
  ],
  templateUrl: './personal-details.component.html',
  styleUrl: './personal-details.component.css'
})
export class PersonalDetailsComponent implements OnInit {
  @Input({required: true}) personalDetails!: PersonalDetails;
  personalDetailsSignal!: WritableSignal<PersonalDetails>;
  personalDetailsForm!: FormGroup;

  constructor(
    private formGeneratorService: FormGeneratorService,
    private personalDetailsService: PersonalDetailsService
  ) {
  }

  ngOnInit(): void {
    this.personalDetailsSignal = signal(this.personalDetails);
    this.personalDetailsForm = this.formGeneratorService.createPersonalDetailsForm();
    this.formGeneratorService.patchPersonalDetails(this.personalDetailsForm,this.personalDetails);
  }

  onSaveClick() {
    this.personalDetailsService.update(
      this.personalDetailsForm.value
    ).subscribe({
      next: updatedPersonalDetails => {
        this.formGeneratorService.patchPersonalDetails(this.personalDetailsForm,updatedPersonalDetails);
        this.personalDetailsSignal.set(updatedPersonalDetails);
      },
      error: err => {
        console.log(err); // TODO dodać obsługę błędów
        this.formGeneratorService.patchPersonalDetails(this.personalDetailsForm,this.personalDetailsSignal());
      }
    });
  }

  cancel() {
    this.formGeneratorService.patchPersonalDetails(this.personalDetailsForm,this.personalDetailsSignal());
  }
}
