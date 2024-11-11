import {Component, Input} from '@angular/core';
import {PrescriptionInfo} from '../../model/prescription-info';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-prescription-card',
  standalone: true,
  imports: [
    DatePipe
  ],
  templateUrl: './prescription-card.component.html',
  styleUrl: './prescription-card.component.css'
})
export class PrescriptionCardComponent {
  @Input({required: true}) prescription!: PrescriptionInfo;

  get getDoctorFullName() {
    return `${this.prescription.doctor.name} ${this.prescription.doctor.surname}`;
  }

  showPrescriptionDetails() {
    console.log(this.prescription.uuid);
  }
}
