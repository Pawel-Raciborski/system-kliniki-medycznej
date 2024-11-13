import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-card-summary',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './card-summary.component.html',
  styleUrl: './card-summary.component.css'
})
export class CardSummaryComponent {

}
