import {Component, Input, OnInit} from '@angular/core';
import {PatientCardService} from '../../../services/patient-card.service';
import {Pagination} from '../../../../pagination/model/pagination';
import {PatientDiseaseHospitalizationInfo} from '../../../model/patient-disease-hospitalization-info';

@Component({
  selector: 'app-patient-hospitalization',
  standalone: true,
  imports: [],
  templateUrl: './patient-hospitalization.component.html',
  styleUrl: './patient-hospitalization.component.css'
})
export class PatientHospitalizationComponent implements OnInit{
  @Input({required:true}) patientCardId!: string;
  patientDiseaseHospitalizationInfoList: PatientDiseaseHospitalizationInfo[] = [];
  pagination: Pagination = {
    page: 0,
    pageSize: 10
  };

  constructor(
    private patientCardService: PatientCardService
  ) {
  }

  ngOnInit(): void {
        this.patientCardService.getHospitalizationHistory(this.patientCardId,this.pagination)
          .subscribe(data => {
            console.log(data);
          });
    }
}
