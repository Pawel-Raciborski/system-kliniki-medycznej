import {Component, Input} from '@angular/core';
import {DatePipe} from "@angular/common";
import {HospitalizationInfo} from '../../../model/hospitalization-info';

@Component({
  selector: 'app-hospitalization-history',
  standalone: true,
    imports: [
        DatePipe
    ],
  templateUrl: './hospitalization-history.component.html',
  styleUrl: './hospitalization-history.component.css'
})
export class HospitalizationHistoryComponent {
  @Input({required:true}) hospitalizationHistory!: HospitalizationInfo;
}
