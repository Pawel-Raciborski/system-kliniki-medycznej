import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogContent, MatDialogRef} from '@angular/material/dialog';
import {
  PatientHospitalizationComponent
} from '../../components/hospitalization/patient-hospitalization/patient-hospitalization.component';

@Component({
  selector: 'app-show-patient-hospitalizations-dialog',
  standalone: true,
  imports: [
    MatDialogContent,
    PatientHospitalizationComponent
  ],
  templateUrl: './show-patient-hospitalizations-dialog.component.html',
  styleUrl: './show-patient-hospitalizations-dialog.component.css'
})
export class ShowPatientHospitalizationsDialogComponent {

  constructor(
    @Inject(MAT_DIALOG_DATA) public patientCardId: string,
    private dialogRef: MatDialogRef<ShowPatientHospitalizationsDialogComponent>
  ) {
  }

  close() {
    this.dialogRef.close();
  }
}
