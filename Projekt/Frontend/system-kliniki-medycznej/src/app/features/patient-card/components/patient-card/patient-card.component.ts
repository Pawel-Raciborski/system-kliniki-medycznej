import { Component } from '@angular/core';
import {CardSummaryComponent} from '../card-summary/card-summary.component';

@Component({
  selector: 'app-patient-card',
  standalone: true,
  imports: [
    CardSummaryComponent
  ],
  templateUrl: './patient-card.component.html',
  styleUrl: './patient-card.component.css'
})
export class PatientCardComponent {

}
