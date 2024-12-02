import { Component } from '@angular/core';
import {CardSummaryComponent} from "../card-summary/card-summary.component";
import {
    PatientHospitalizationComponent
} from "../hospitalization/patient-hospitalization/patient-hospitalization.component";

@Component({
  selector: 'app-doctor-patient-card',
  standalone: true,
    imports: [
        CardSummaryComponent,
        PatientHospitalizationComponent
    ],
  templateUrl: './doctor-patient-card.component.html',
  styleUrl: './doctor-patient-card.component.css'
})
export class DoctorPatientCardComponent {

}
