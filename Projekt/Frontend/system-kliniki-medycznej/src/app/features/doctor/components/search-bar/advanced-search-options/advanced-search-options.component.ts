import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormArray, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {DoctorSpecializationService} from '../../../../doctor-specialization/services/doctor-specialization.service';
import {MatFormField, MatLabel} from '@angular/material/form-field';
import {MatInput} from '@angular/material/input';
import {MatOption, MatSelect} from '@angular/material/select';

@Component({
  selector: 'app-advanced-search-options',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormField,
    MatInput,
    MatLabel,
    MatSelect,
    MatOption
  ],
  templateUrl: './advanced-search-options.component.html',
  styleUrl: './advanced-search-options.component.css'
})
export class AdvancedSearchOptionsComponent implements OnInit{
  advancedSearchForm!: FormGroup;
  availableSpecializations: string[] = [];
  @Output() private advancedFormEmitter = new EventEmitter<FormGroup>();

  constructor(
    private doctorSpecializationService: DoctorSpecializationService,
  ) {
    this.advancedSearchForm = new FormGroup({
      pwzNumber: new FormControl(''),
      selectedSpecializations: new FormControl<string[]>([])
    });
  }
  ngOnInit(): void {
    this.initDoctorSpecializations();
    this.advancedFormEmitter.emit(this.advancedSearchForm);
  }


  private initDoctorSpecializations() {
    this.doctorSpecializationService.getAllAvailableSpecializationNames().subscribe(
      data => {
        this.availableSpecializations = data;
      }
    );
  }
}
