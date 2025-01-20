import {Component, EventEmitter, Input, Output} from '@angular/core';
import {DiseaseDto} from '../../model/disease-dto';

@Component({
  selector: 'app-disease-table',
  standalone: true,
  imports: [],
  templateUrl: './disease-table.component.html',
  styleUrl: './disease-table.component.css'
})
export class DiseaseTableComponent {
  @Input() diseases!: DiseaseDto[];
  @Output() diseaseToAddEmitter = new EventEmitter<DiseaseDto>();
  addDisease(disease: DiseaseDto) {
    this.diseaseToAddEmitter.emit(disease);
  }
}
