import {Component, EventEmitter, Output} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {SearchDisease} from '../../model/search-disease';

@Component({
  selector: 'app-search-disease',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './search-disease.component.html',
  styleUrl: './search-disease.component.css'
})
export class SearchDiseaseComponent {
  from: FormGroup = new FormGroup({
    name: new FormControl(''),
    code: new FormControl('')
  });

  @Output() searchDiseaseEmitter = new EventEmitter<SearchDisease>();

  search() {
    this.searchDiseaseEmitter.emit({...this.from.value});
  }
}
