import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogContent, MatDialogRef} from '@angular/material/dialog';
import {HospitalizationInfo} from '../../../model/hospitalization-info';
import {Pagination} from '../../../../pagination/model/pagination';
import {DatePipe} from '@angular/common';
import {HospitalizationHistoryComponent} from '../hospitalization-history/hospitalization-history.component';
import {TableOptionsComponent} from '../../../../doctor/components/doctor-table/table-options/table-options.component';
import {PatientDiseaseService} from '../../../../patient-disease/services/patient-disease.service';

@Component({
  selector: 'app-hospitalization-table-history',
  standalone: true,
  imports: [
    MatDialogContent,
    DatePipe,
    HospitalizationHistoryComponent,
    TableOptionsComponent
  ],
  templateUrl: './hospitalization-table-history.component.html',
  styleUrl: './hospitalization-table-history.component.css'
})
export class HospitalizationTableHistoryComponent implements OnInit {
  hospitalizationHistoryList: HospitalizationInfo[] = [];
  pagination: Pagination = {
    page: 0,
    pageSize: 10
  };
  lastLoadedPageSize = this.pagination.pageSize;


  constructor(
    @Inject(MAT_DIALOG_DATA) private patientDiseaseId: number,
    private patientDiseaseService: PatientDiseaseService,
    private currentDialog: MatDialogRef<HospitalizationTableHistoryComponent>
  ) {

  }

  ngOnInit(): void {
    this.loadFirstPageHospitalizationHistory();
  }

  private loadFirstPageHospitalizationHistory() {
    this.patientDiseaseService.getHospitalizationHistory(this.patientDiseaseId, this.pagination).subscribe(
      hospitalizations => {
        this.hospitalizationHistoryList = hospitalizations;
        this.lastLoadedPageSize = hospitalizations.length;
      }
    );
  }

  loadMore() {
    this.pagination.page++;
    this.loadMoreHospitalizationHistory();
  }

  onPageSizeChange(newPageSize: number) {
    console.log("PageChange")

    this.pagination.pageSize = +newPageSize;
    this.pagination.page = 0;
    console.log('pagination: ',this.pagination);
    this.loadFirstPageHospitalizationHistory();
  }

  private loadMoreHospitalizationHistory() {
    this.patientDiseaseService.getHospitalizationHistory(this.patientDiseaseId, this.pagination).subscribe(
      hospitalizations => {
        this.hospitalizationHistoryList.push(...hospitalizations);
        this.lastLoadedPageSize = hospitalizations.length;
      }
    );
  }

  close() {
    this.currentDialog.close();
  }
}
