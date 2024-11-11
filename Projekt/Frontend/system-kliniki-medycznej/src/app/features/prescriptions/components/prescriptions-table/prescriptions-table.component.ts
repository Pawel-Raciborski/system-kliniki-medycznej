import {Component, Input} from '@angular/core';
import {PrescriptionInfo} from '../../model/prescription-info';
import {DoctorInfo} from '../../../doctor/domain/doctor-info';
import {DoctorInfoDialogComponent} from '../../../doctor/dialogs/doctor-info-dialog/doctor-info-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {PrescriptionCardComponent} from '../prescription-card/prescription-card.component';

@Component({
  selector: 'app-prescriptions-table',
  standalone: true,
  imports: [
    PrescriptionCardComponent
  ],
  templateUrl: './prescriptions-table.component.html',
  styleUrl: './prescriptions-table.component.css'
})
export class PrescriptionsTableComponent {
  @Input({required: true}) prescriptions!: PrescriptionInfo[];

  constructor(private dialog: MatDialog) {
  }
}
