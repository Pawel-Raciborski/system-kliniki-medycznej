import {Component, EventEmitter, Output} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {SearchMedicineData} from '../../model/search-medicine-data';

@Component({
  selector: 'app-search-medicine-bar',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './search-medicine-bar.component.html',
  styleUrl: './search-medicine-bar.component.css'
})
export class SearchMedicineBarComponent {
  showAdvancedSearch = false;
  formGroup: FormGroup = new FormGroup({
    medicinalProductName: new FormControl('')
  });

  @Output() searchFormEmitter = new EventEmitter<SearchMedicineData>();

  show() {
    this.showAdvancedSearch = !this.showAdvancedSearch;

    if(!this.showAdvancedSearch){
      this.formGroup.removeControl('advancedSearchOptions');
    }else{
      this.addAdvancedOptionsToForm()
    }
  }

  private addAdvancedOptionsToForm() {
    this.formGroup.addControl('advancedSearchOptions', new FormGroup({
      atcCode: new FormControl(''),
      commonName: new FormControl(''),
      gtinNumber: new FormControl('')
    }));
  }

  search() {
    this.searchFormEmitter.emit(this.formGroup.value);
  }
}
