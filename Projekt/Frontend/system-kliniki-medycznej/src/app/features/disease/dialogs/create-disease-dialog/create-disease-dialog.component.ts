import {Component} from '@angular/core';
import {DiseaseListComponent} from '../../components/disease-list/disease-list.component';
import {MatDialogRef} from '@angular/material/dialog';
import {DiseaseDto} from '../../model/disease-dto';
import {CreatePatientDiseasePart} from '../../model/create-patient-disease-part';

@Component({
  selector: 'app-create-disease-dialog',
  standalone: true,
  imports: [
    DiseaseListComponent
  ],
  templateUrl: './create-disease-dialog.component.html',
  styleUrl: './create-disease-dialog.component.css'
})
export class CreateDiseaseDialogComponent {

  constructor(
    private dialogRef: MatDialogRef<CreateDiseaseDialogComponent>
  ) {
  }

  returnDisease(diseaseWithDescription: CreatePatientDiseasePart) {
    this.dialogRef.close(diseaseWithDescription);
  }

  cancel() {
    this.dialogRef.close();
  }
}
