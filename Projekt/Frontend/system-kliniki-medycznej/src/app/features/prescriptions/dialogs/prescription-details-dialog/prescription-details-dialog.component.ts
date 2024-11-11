import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {PrescriptionService} from '../../services/prescription.service';
import {PrescriptionDetails} from '../../model/prescription-details';
import {DoctorInfoDialogComponent} from '../../../doctor/dialogs/doctor-info-dialog/doctor-info-dialog.component';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-prescription-details-dialog',
  standalone: true,
  imports: [
    DatePipe
  ],
  templateUrl: './prescription-details-dialog.component.html',
  styleUrl: './prescription-details-dialog.component.css'
})
export class PrescriptionDetailsDialogComponent implements OnInit {
  prescriptionDetails!: PrescriptionDetails;

  constructor(
    @Inject(MAT_DIALOG_DATA) public prescriptionId: string,
    private prescriptionService: PrescriptionService,
    private dialogRef: MatDialogRef<PrescriptionDetailsDialogComponent>,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.prescriptionService.getPrescriptionDetails(this.prescriptionId)
      .subscribe(prescriptionDetails => {
        this.prescriptionDetails = prescriptionDetails;
      });
  }

  get getDoctorFullName(){
    return `${this.prescriptionDetails.doctorInfo.name} ${this.prescriptionDetails.doctorInfo.surname}`
  }

  closeModal() {
    this.dialogRef.close();
  }

  showDoctorDetails() {
    this.dialog.open(DoctorInfoDialogComponent,{
      data: {
        doctorInfo: this.prescriptionDetails.doctorInfo,
        showCreateAppointmentButton: false
      }
    });
  }
}
