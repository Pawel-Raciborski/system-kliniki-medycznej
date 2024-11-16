import {Component, Input, OnInit} from '@angular/core';
import {PatientCardService} from '../../../services/patient-card.service';
import {Pagination} from '../../../../pagination/model/pagination';
import {PatientDiseaseHospitalizationInfo} from '../../../model/patient-disease-hospitalization-info';
import {HospitalizationDetailsComponent} from '../hospitalization-details/hospitalization-details.component';

@Component({
  selector: 'app-patient-hospitalization',
  standalone: true,
  imports: [
    HospitalizationDetailsComponent
  ],
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
            this.patientDiseaseHospitalizationInfoList = data;
          });
    }

  loadMoreData() {

  }
}
