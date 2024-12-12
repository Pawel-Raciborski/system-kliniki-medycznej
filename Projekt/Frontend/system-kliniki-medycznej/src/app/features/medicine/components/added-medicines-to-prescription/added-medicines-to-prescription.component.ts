import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
import {PrescriptionMedicine} from '../../../prescriptions/model/prescription-medicine';

@Component({
  selector: 'app-added-medicines-to-prescription',
  standalone: true,
  imports: [],
  templateUrl: './added-medicines-to-prescription.component.html',
  styleUrl: './added-medicines-to-prescription.component.css'
})
export class AddedMedicinesToPrescriptionComponent {
  @Input() addedMedicines!: PrescriptionMedicine[];
  @Output() removedMedicineEmitter = new EventEmitter<PrescriptionMedicine>();

  removeMedicine(medicine: PrescriptionMedicine) {
    this.removedMedicineEmitter.emit(medicine);
  }
}
