import {Component, Input, OnInit} from '@angular/core';
import {PatientCardService} from '../../../services/patient-card.service';
import {Pagination} from '../../../../pagination/model/pagination';
import {PatientDiseaseHospitalizationInfo} from '../../../model/patient-disease-hospitalization-info';
import {HospitalizationDetailsComponent} from '../hospitalization-details/hospitalization-details.component';
import {TableOptionsComponent} from '../../../../doctor/components/doctor-table/table-options/table-options.component';
import {PatientDiseaseService} from '../../../../patient-disease/services/patient-disease.service';

@Component({
  selector: 'app-patient-hospitalization',
  standalone: true,
  imports: [
    HospitalizationDetailsComponent,
    TableOptionsComponent
  ],
  templateUrl: './patient-hospitalization.component.html',
  styleUrl: './patient-hospitalization.component.css'
})
export class PatientHospitalizationComponent implements OnInit {
  @Input({required: true}) patientCardId!: string;
  patientDiseaseHospitalizationInfoList: PatientDiseaseHospitalizationInfo[] = [];

  pagination: Pagination = {
    page: 0,
    pageSize: 10
  };
  lastLoadedPageSize: number = 10;

  constructor(
    private patientDiseaseService: PatientDiseaseService
  ) {
  }

  ngOnInit(): void {
    this.patientDiseaseService.getHospitalizations(this.patientCardId, this.pagination)
      .subscribe(data => {
        this.patientDiseaseHospitalizationInfoList = data;
        this.lastLoadedPageSize = data.length;
      });
  }

  loadMoreData() {

  }
}
