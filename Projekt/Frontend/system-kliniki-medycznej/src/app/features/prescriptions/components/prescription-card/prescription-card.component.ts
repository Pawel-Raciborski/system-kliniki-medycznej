import {Component, Input} from '@angular/core';
import {PrescriptionInfo} from '../../model/prescription-info';
import {DatePipe} from '@angular/common';
import {MatDialog} from '@angular/material/dialog';
import {
  PrescriptionDetailsDialogComponent
} from '../../dialogs/prescription-details-dialog/prescription-details-dialog.component';

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

  constructor(
    private dialog: MatDialog
  ) {
  }

  get getDoctorFullName() {
    return `${this.prescription.doctor.name} ${this.prescription.doctor.surname}`;
  }

  showPrescriptionDetails() {
    console.log(this.prescription.uuid);
    this.dialog.open(PrescriptionDetailsDialogComponent,{
      data: this.prescription.uuid,
      minWidth: '600px',
    });
  }
}
