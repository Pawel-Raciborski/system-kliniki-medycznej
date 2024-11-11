import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {PrescriptionService} from '../../services/prescription.service';

@Component({
  selector: 'app-prescription-details-dialog',
  standalone: true,
  imports: [],
  templateUrl: './prescription-details-dialog.component.html',
  styleUrl: './prescription-details-dialog.component.css'
})
export class PrescriptionDetailsDialogComponent {

  constructor(
    @Inject(MAT_DIALOG_DATA) public prescriptionId: string,
    private prescriptionService: PrescriptionService,
  ) {
  }
}
