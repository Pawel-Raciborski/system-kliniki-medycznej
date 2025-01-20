import {Component, Input} from '@angular/core';
import {PatientDiseaseHospitalizationInfo} from '../../../model/patient-disease-hospitalization-info';
import {DatePipe} from '@angular/common';
import {MatDialog} from '@angular/material/dialog';
import {
  HospitalizationTableHistoryComponent
} from '../hospitalization-table-history/hospitalization-table-history.component';
import {UserService} from '../../../../auth/services/user.service';
import {
  UpdateHospitalizationDialogComponent
} from '../../../dialogs/update-hospitalization-dialog/update-hospitalization-dialog.component';
import {HospitalizationService} from '../../../../patient-disease/services/hospitalization.service';
import {HospitalizationInfo} from '../../../model/hospitalization-info';

@Component({
  selector: 'app-hospitalization-details',
  standalone: true,
  imports: [
    DatePipe
  ],
  templateUrl: './hospitalization-details.component.html',
  styleUrl: './hospitalization-details.component.css'
})
export class HospitalizationDetailsComponent {
  @Input({required: true}) patientDiseaseHospitalizationInfo!: PatientDiseaseHospitalizationInfo;
  showPrescriptions = false;

  constructor(
    private dialog: MatDialog,
    public userService: UserService,
    private hospitalizationService: HospitalizationService
  ) {
  }

  get getMedicine() {
    return this.patientDiseaseHospitalizationInfo.currentHospitalization.medicine;
  }

  get getHospitalization() {
    return this.patientDiseaseHospitalizationInfo.currentHospitalization;
  }

  get getDiseaseInfo() {
    return this.patientDiseaseHospitalizationInfo.diseaseInfo;
  }

  displayPrescriptions() {
    this.showPrescriptions = !this.showPrescriptions;
  }

  showHospitalizationHistory() {
    this.dialog.open(HospitalizationTableHistoryComponent, {
      data: this.patientDiseaseHospitalizationInfo.diseaseId,
      minWidth: '800px'
    });
  }

  updateHospitalization() {
    this.dialog.open(UpdateHospitalizationDialogComponent, {
      data: this.getHospitalization
    }).afterClosed().subscribe((hospitalizationToUpdate: HospitalizationInfo) => {
      if (hospitalizationToUpdate) {
        this.hospitalizationService.updateDiseaseHospitalization(hospitalizationToUpdate).subscribe(
          updatedHospitalization => {
            this.patientDiseaseHospitalizationInfo.currentHospitalization = updatedHospitalization;
          }
        );
      }
    })
  }

  public isBefore(){
    if(!this.patientDiseaseHospitalizationInfo.currentHospitalization.finishDate){
      return true;
    }
    let now = Date.now();
    let date = new Date(this.patientDiseaseHospitalizationInfo.currentHospitalization.finishDate).getTime();
    return now <= date;
  }
}
