import {Component, Input} from '@angular/core';
import {PatientDiseaseHospitalizationInfo} from '../../../model/patient-disease-hospitalization-info';
import {DatePipe} from '@angular/common';
import {MatDialog} from '@angular/material/dialog';
import {
  HospitalizationTableHistoryComponent
} from '../hospitalization-table-history/hospitalization-table-history.component';

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
  showPrescriptions=false;

  constructor(
    private dialog: MatDialog
  ) {
  }

  get getMedicine(){
    return this.patientDiseaseHospitalizationInfo.currentHospitalization.medicine;
  }

  get getHospitalization(){
    return this.patientDiseaseHospitalizationInfo.currentHospitalization;
  }

  get getDiseaseInfo(){
    return this.patientDiseaseHospitalizationInfo.diseaseInfo;
  }

  displayPrescriptions() {
    this.showPrescriptions=!this.showPrescriptions;
  }

  showHospitalizationHistory() {
    this.dialog.open(HospitalizationTableHistoryComponent, {
      data: this.patientDiseaseHospitalizationInfo.id,
      minWidth: '800px'
    });
  }
}
