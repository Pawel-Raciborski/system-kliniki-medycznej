import {Component, EventEmitter, Output} from '@angular/core';
import {AdvancedSearchOptionsComponent} from '../advanced-search-options/advanced-search-options.component';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {DoctorInfo} from '../../../domain/doctor-info';
import {DoctorService} from '../../../services/doctor.service';

@Component({
  selector: 'app-doctor-search-bar',
  standalone: true,
  imports: [
    AdvancedSearchOptionsComponent,
    ReactiveFormsModule
  ],
  templateUrl: './doctor-search-bar.component.html',
  styleUrl: './doctor-search-bar.component.css'
})
export class DoctorSearchBarComponent {
  showAdvancedSearch = false;
  doctorSearchForm: FormGroup = new FormGroup({
    searchValue: new FormControl('')
  });
  advancedSearchForm!: FormGroup;
  @Output() doctorSearchOptionsEmitter = new EventEmitter<FormGroup>();

  constructor(private doctorService:DoctorService
  ) {
  }

  changeAdvancedStatus() {
    if(this.showAdvancedSearch){
      this.doctorSearchForm.removeControl('advancedSearchForm');
    }

    this.showAdvancedSearch = !this.showAdvancedSearch;
  }

  addAdvancedSearchToForm(data: FormGroup) {
    this.doctorSearchForm.addControl('advancedSearchForm', data);
  }

  search() {
    this.doctorSearchOptionsEmitter.emit(this.doctorSearchForm);
  }
}
