import {Component, EventEmitter, Output} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {SearchPatient} from '../../model/search-patient';

@Component({
  selector: 'app-patient-search-bar',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './patient-search-bar.component.html',
  styleUrl: './patient-search-bar.component.css'
})
export class PatientSearchBarComponent {
  searchForm: FormGroup = new FormGroup({
    pesel: new FormControl('')
  });

  @Output() searchEmitter = new EventEmitter<SearchPatient>();

  onPatientSearch() {
    this.searchEmitter.emit(this.buildSearchPatient())
  }

  private buildSearchPatient(): SearchPatient {
    return {
      pesel: this.searchForm.value.pesel
    };
  }
}
