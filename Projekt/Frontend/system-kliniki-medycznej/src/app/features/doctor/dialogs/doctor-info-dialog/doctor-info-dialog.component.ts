import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogContent, MatDialogRef} from '@angular/material/dialog';
import {DoctorInfo} from '../../domain/doctor-info';
import {DoctorService} from '../../services/doctor.service';
import {DoctorSpecializationService} from '../../../doctor-specialization/services/doctor-specialization.service';
import {
  MakeAppointmentDialogComponent
} from '../../../appointment/dialogs/make-appointment-dialog/make-appointment-dialog.component';
import {UserService} from '../../../auth/services/user.service';
import {DoctorInfoDialogDataWithConfig} from '../models/doctor-info-dialog-data-with-config';
import {DoctorSpecialization} from '../../domain/doctor-specialization';

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
  doctorSpecializations: DoctorSpecialization[] = [];
  doctorInfo!: DoctorInfo;
  showButton: boolean = true;

  constructor(
    @Inject(MAT_DIALOG_DATA) private doctorInfoDialogDataWithConfig: DoctorInfoDialogDataWithConfig,
    private doctorSpecializationService: DoctorSpecializationService,
    private dialogRef: MatDialogRef<DoctorInfoDialogComponent>,
    private dialog: MatDialog,
    public userService: UserService
  ) {
  }

  ngOnInit(): void {
    this.initDoctorInfoConfig();
    this.doctorSpecializationService.getDoctorSpecializations(this.doctorInfo.pwzNumber).subscribe(
      specializationNames => {
        this.doctorSpecializations = specializationNames;
      }
    );
    this.initShowButton();
  }

  close() {
    this.dialogRef.close();
  }

  openAppointmentDialog() {
    this.dialog.open(MakeAppointmentDialogComponent, {
      data: this.doctorInfo,
      width: '700px'
    }).afterClosed().subscribe(notification => {
      this.dialogRef.close(notification);
    });
  }

  private initDoctorInfoConfig() {
    this.doctorInfo = this.doctorInfoDialogDataWithConfig.doctorInfo;
  }

  private initShowButton() {
    this.showButton = this.doctorInfoDialogDataWithConfig.showCreateAppointmentButton;
  }
}
