import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {MedicineDto} from '../../../medicine/model/medicine-dto';

@Component({
  selector: 'app-show-creating-prescription-details-dialog',
  standalone: true,
  imports: [],
  templateUrl: './show-creating-prescription-details-dialog.component.html',
  styleUrl: './show-creating-prescription-details-dialog.component.css'
})
export class ShowCreatingPrescriptionDetailsDialogComponent {

  constructor(
    @Inject(MAT_DIALOG_DATA) public medicines: { registryNumber: string; dosage: string }[]
  ) {
  }
}
