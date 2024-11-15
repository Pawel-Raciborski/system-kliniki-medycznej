import {Component, Input, OnInit} from '@angular/core';
import {RouterLink} from '@angular/router';
import {PatientCardService} from '../../services/patient-card.service';
import {CardSummary} from '../../model/card-summary';

@Component({
  selector: 'app-card-summary',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './card-summary.component.html',
  styleUrl: './card-summary.component.css'
})
export class CardSummaryComponent implements OnInit{
  @Input({required:true}) patientCardId!: string;
  cardSummary!: CardSummary;
  constructor(private patientCardService: PatientCardService) {
  }

  ngOnInit(): void {
        this.patientCardService.getCardSummary(this.patientCardId).subscribe(
          data => {
            this.cardSummary = data;
          }
        );
    }
}
