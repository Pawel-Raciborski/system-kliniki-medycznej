import {Component, Input, OnInit} from '@angular/core';
import {CardSummaryComponent} from '../card-summary/card-summary.component';
import {PatientHospitalizationComponent} from '../hospitalization/patient-hospitalization/patient-hospitalization.component';
import {PatientCardService} from '../../services/patient-card.service';
import {UserService} from '../../../auth/services/user.service';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-patient-card',
  standalone: true,
  imports: [
    CardSummaryComponent,
    PatientHospitalizationComponent,
    NgIf
  ],
  templateUrl: './patient-card.component.html',
  styleUrl: './patient-card.component.css'
})
export class PatientCardComponent implements OnInit{
  patientCard = '';
  constructor(
    private patientCardService: PatientCardService,
    private userService: UserService
  ) {
  }

  ngOnInit(): void {
    this.patientCardService.findPatientCard(this.userService.getId("patientId")).subscribe(d => {
      this.patientCard = d;
    });
  }
}
