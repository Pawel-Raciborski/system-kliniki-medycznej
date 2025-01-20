import {Component, Input, OnInit} from '@angular/core';
import {PatientData} from '../../model/patient-data';
import {DatePipe} from '@angular/common';
import {PatientDetails} from '../../model/patient-details';
import {PatientsService} from '../../services/patients.service';

@Component({
  selector: 'app-patient-data',
  standalone: true,
  imports: [
    DatePipe
  ],
  templateUrl: './patient-data.component.html',
  styleUrl: './patient-data.component.css'
})
export class PatientDataComponent implements OnInit{
  @Input() patientData!: PatientData;
  patientDetails!: PatientDetails

  constructor(
    private patientsService: PatientsService
  ) {
  }

  get personalDetails(){
    return this.patientData.personalDetails;
  }

  ngOnInit(): void {
    this.patientsService.findPatientDetails(this.patientData.personalDetails.pesel).subscribe(
      data => {
        this.patientDetails = data;
      }
    );
  }
}
