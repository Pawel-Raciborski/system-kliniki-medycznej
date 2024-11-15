import {Component, OnInit} from '@angular/core';
import {CardSummaryComponent} from '../card-summary/card-summary.component';
import {PatientHospitalizationComponent} from '../hospitalization/patient-hospitalization/patient-hospitalization.component';
import {PatientCardService} from '../../services/patient-card.service';

@Component({
  selector: 'app-patient-card',
  standalone: true,
  imports: [
    CardSummaryComponent,
    PatientHospitalizationComponent
  ],
  templateUrl: './patient-card.component.html',
  styleUrl: './patient-card.component.css'
})
export class PatientCardComponent implements OnInit{

  constructor(private patientCardService: PatientCardService) {
  }

  ngOnInit(): void {
    this.patientCardService.findPatientCard().subscribe(d => {
      console.log(d);
    });
  }
}
