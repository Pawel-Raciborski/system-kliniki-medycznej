import {Component, Input, OnInit} from '@angular/core';
import {PersonalDetails} from '../../domain/personal-details';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {FormGeneratorService} from '../../../../services/form-generator.service';

@Component({
  selector: 'app-personal-details',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './personal-details.component.html',
  styleUrl: './personal-details.component.css'
})
export class PersonalDetailsComponent implements OnInit {
  @Input({required: true}) personalDetails!: PersonalDetails;
  personalDetailsForm!: FormGroup;

  constructor(private formGeneratorService: FormGeneratorService) {
  }

  ngOnInit(): void {
    this.personalDetailsForm = this.formGeneratorService.patchValues(this.personalDetails);
  }
}
