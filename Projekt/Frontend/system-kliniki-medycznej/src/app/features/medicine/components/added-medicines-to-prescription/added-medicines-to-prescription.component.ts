import {Component, Input, OnInit} from '@angular/core';
import {MedicineDto} from '../../model/medicine-dto';
import {FormArray, FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-added-medicines-to-prescription',
  standalone: true,
  imports: [],
  templateUrl: './added-medicines-to-prescription.component.html',
  styleUrl: './added-medicines-to-prescription.component.css'
})
export class AddedMedicinesToPrescriptionComponent {
  @Input() addedMedicines: MedicineDto[] = [];
  constructor(private fb: FormBuilder) {
  }

  removeMedicine(medicine: MedicineDto) {

  }
}
