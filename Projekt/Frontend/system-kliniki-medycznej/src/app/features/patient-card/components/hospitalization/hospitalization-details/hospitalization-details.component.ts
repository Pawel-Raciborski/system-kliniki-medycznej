import {Component, Input} from '@angular/core';
import {PatientDiseaseHospitalizationInfo} from '../../../model/patient-disease-hospitalization-info';
import {DatePipe} from '@angular/common';

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

  get getMedicine(){
    return this.patientDiseaseHospitalizationInfo.currentHospitalization.medicine;
  }

  get getHospitalization(){
    return this.patientDiseaseHospitalizationInfo.currentHospitalization;
  }

  get getDiseaseInfo(){
    return this.patientDiseaseHospitalizationInfo.diseaseInfo;
  }
}