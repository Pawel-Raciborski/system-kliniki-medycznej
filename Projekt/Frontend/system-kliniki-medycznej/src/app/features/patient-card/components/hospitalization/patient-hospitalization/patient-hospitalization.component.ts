import {Component, Input, OnInit} from '@angular/core';
import {PatientCardService} from '../../../services/patient-card.service';
import {Pagination} from '../../../../pagination/model/pagination';
import {PatientDiseaseHospitalizationInfo} from '../../../model/patient-disease-hospitalization-info';
import {HospitalizationDetailsComponent} from '../hospitalization-details/hospitalization-details.component';
import {TableOptionsComponent} from '../../../../doctor/components/doctor-table/table-options/table-options.component';
import {HospitalizationService} from '../../../../patient-disease/services/hospitalization.service';
import {SearchDiseaseComponent} from '../../../../disease/components/search-disease/search-disease.component';
import {SearchDisease} from '../../../../disease/model/search-disease';
import {
  CreateDiseaseDialogComponent
} from '../../../../disease/dialogs/create-disease-dialog/create-disease-dialog.component';
import {CreatePatientDiseasePart} from '../../../../disease/model/create-patient-disease-part';
import {MatDialog} from '@angular/material/dialog';
import {UserService} from '../../../../auth/services/user.service';

@Component({
  selector: 'app-patient-hospitalization',
  standalone: true,
  imports: [
    HospitalizationDetailsComponent,
    TableOptionsComponent,
    SearchDiseaseComponent
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
    private hospitalizationService: HospitalizationService,
    private dialog: MatDialog,
    public userService: UserService
  ) {
  }

  ngOnInit(): void {
    this.hospitalizationService.getPatientDiseaseHospitalizations(this.patientCardId, this.pagination)
      .subscribe(data => {
        this.patientDiseaseHospitalizationInfoList = data;
        this.lastLoadedPageSize = data.length;
      });
  }

  loadMoreData() {

  }

  searchDiseases(searchDisease: SearchDisease) {
    console.log(searchDisease);
    this.clearPagination();
    this.hospitalizationService.searchSpecifiedPatientDiseaseHospitalizations(this.patientCardId,searchDisease,this.pagination).subscribe(
      data => {
        this.lastLoadedPageSize = data.length;
        this.patientDiseaseHospitalizationInfoList = data;
      }
    )
  }

  private clearPagination() {
    this.pagination.page = 0;
    this.pagination.pageSize = 10;
  }

}
