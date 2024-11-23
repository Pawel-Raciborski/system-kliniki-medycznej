import {Component, EventEmitter, Input, Output} from '@angular/core';
import {MedicineDto} from '../../model/medicine-dto';

@Component({
  selector: 'app-found-medicines',
  standalone: true,
  imports: [],
  templateUrl: './found-medicines.component.html',
  styleUrl: './found-medicines.component.css'
})
export class FoundMedicinesComponent {
  @Input() medicines: MedicineDto[] = [];
  @Output() medicineToAddEmitter = new EventEmitter<MedicineDto>();

  addMedicine(medicine: MedicineDto) {
    this.removeMedicine(medicine);
    this.medicineToAddEmitter.emit(medicine);
  }

  private removeMedicine(medicine: MedicineDto) {
    this.medicines = this.medicines.filter(m => m.id !== medicine.id);
  }
}
