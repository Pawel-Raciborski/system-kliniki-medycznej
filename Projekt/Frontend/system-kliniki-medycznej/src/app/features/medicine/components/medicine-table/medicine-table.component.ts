import {Component, Input} from '@angular/core';
import {MedicineDto} from '../../model/medicine-dto';

@Component({
  selector: 'app-medicine-table',
  standalone: true,
  imports: [],
  templateUrl: './medicine-table.component.html',
  styleUrl: './medicine-table.component.css'
})
export class MedicineTableComponent {
  @Input() medicines!: MedicineDto[];
}
