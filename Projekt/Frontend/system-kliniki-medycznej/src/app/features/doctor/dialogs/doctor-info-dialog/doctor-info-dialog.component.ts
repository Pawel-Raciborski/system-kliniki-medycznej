import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogContent, MatDialogRef} from '@angular/material/dialog';
import {DoctorInfo} from '../../domain/doctor-info';
import {DoctorService} from '../../services/doctor.service';
import {DoctorSpecializationService} from '../../../doctor-specialization/services/doctor-specialization.service';

@Component({
  selector: 'app-doctor-info-dialog',
  standalone: true,
  imports: [
    MatDialogContent
  ],
  templateUrl: './doctor-info-dialog.component.html',
  styleUrl: './doctor-info-dialog.component.css'
})
export class DoctorInfoDialogComponent implements OnInit {
  doctorSpecializations: string[] = [];
  constructor(
    @Inject(MAT_DIALOG_DATA) public doctorInfo: DoctorInfo,
    private doctorSpecializationService: DoctorSpecializationService,
    private dialogRef: MatDialogRef<DoctorInfoDialogComponent>
  ) {
  }

  ngOnInit(): void {
    this.doctorSpecializationService.getAllAvailableSpecializationNames().subscribe(
      specializationNames => {
        this.doctorSpecializations = specializationNames;
      }
    )
  }
  close() {
    this.dialogRef.close();
  }
}
