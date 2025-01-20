import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogContent, MatDialogRef} from '@angular/material/dialog';
import {HospitalizationInfo} from '../../../model/hospitalization-info';
import {Pagination} from '../../../../pagination/model/pagination';
import {DatePipe} from '@angular/common';
import {HospitalizationHistoryComponent} from '../hospitalization-history/hospitalization-history.component';
import {TableOptionsComponent} from '../../../../doctor/components/doctor-table/table-options/table-options.component';
import {HospitalizationService} from '../../../../patient-disease/services/hospitalization.service';
import {UserService} from '../../../../auth/services/user.service';
import {
  AddHospitalizationDialogComponent
} from '../../../dialogs/add-hospitalization-dialog/add-hospitalization-dialog.component';
import {CreateHospitalizationRequest} from '../../../model/create-hospitalization-request';
import {MedicineDto} from '../../../../medicine/model/medicine-dto';

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
    private hospitalizationService: HospitalizationService,
    private currentDialog: MatDialogRef<HospitalizationTableHistoryComponent>,
    private dialog: MatDialog,
    public userService: UserService
  ) {

  }

  ngOnInit(): void {
    this.loadFirstPageHospitalizationHistory();
  }

  private loadFirstPageHospitalizationHistory() {
    this.hospitalizationService.getHospitalizationHistory(this.patientDiseaseId, this.pagination).subscribe(
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
    console.log('pagination: ', this.pagination);
    this.loadFirstPageHospitalizationHistory();
  }

  private loadMoreHospitalizationHistory() {
    this.hospitalizationService.getHospitalizationHistory(this.patientDiseaseId, this.pagination).subscribe(
      hospitalizations => {
        this.hospitalizationHistoryList.push(...hospitalizations);
        this.lastLoadedPageSize = hospitalizations.length;
      }
    );
  }

  close() {
    this.currentDialog.close();
  }

  openAddHospitalizationDialog() {
    this.dialog.open(AddHospitalizationDialogComponent).afterClosed().subscribe((data: {
      medicine: MedicineDto,
      dosage: string,
      notes: string,
      finishDate: string
    }) => {
      if(data){
        let hospitalizationRequest= this.hospitalizationService.buildHospitalizationRequest(data,this.patientDiseaseId);
        this.hospitalizationService.create(hospitalizationRequest).subscribe(createdHospitalization => {
          this.addToArray(createdHospitalization);
        });
      }
    });
  }

  private addToArray(createdHospitalization: HospitalizationInfo) {
    this.hospitalizationHistoryList.unshift(createdHospitalization);
  }

  updateHospitalization(hospitalizationInfo: HospitalizationInfo) {
    this.hospitalizationService.updateDiseaseHospitalization(hospitalizationInfo).subscribe(
      updatedHospitalization => {
      }
    )
  }
}
