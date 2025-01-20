import {Component, Input, OnInit} from '@angular/core';
import {CardSummaryComponent} from "../card-summary/card-summary.component";
import {
  PatientHospitalizationComponent
} from "../hospitalization/patient-hospitalization/patient-hospitalization.component";
import {PatientCardService} from '../../services/patient-card.service';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-doctor-patient-card',
  standalone: true,
  imports: [
    CardSummaryComponent,
    PatientHospitalizationComponent,
    NgIf
  ],
  templateUrl: './doctor-patient-card.component.html',
  styleUrl: './doctor-patient-card.component.css'
})
export class DoctorPatientCardComponent implements OnInit {
  @Input() id!: number;
  patientCardId!: string;

  constructor(
    private patientCardService: PatientCardService
  ) {

  }

  ngOnInit(): void {
    this.patientCardService.findPatientCard(this.id).subscribe(patientCard => {
      console.log('PATIENTCARD',patientCard);
      this.patientCardId = patientCard;
    });
  }

}
