import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogContent} from '@angular/material/dialog';
import {DoctorInfo} from '../../domain/doctor-info';
import {DoctorService} from '../../services/doctor.service';

@Component({
  selector: 'app-doctor-info-dialog',
  standalone: true,
  imports: [
    MatDialogContent
  ],
  templateUrl: './doctor-info-dialog.component.html',
  styleUrl: './doctor-info-dialog.component.css'
})
export class DoctorInfoDialogComponent implements OnInit{
  doctorInfo!: DoctorInfo;

  constructor(
    private doctorService: DoctorService,
    @Inject(MAT_DIALOG_DATA) private appointmentId: string
  ) {
  }

  ngOnInit(): void {
    console.log(this.appointmentId);
    this.doctorService.getAppointmentDoctorDetails(this.appointmentId)
      .subscribe(data => {
        this.doctorInfo = data;
      });
  }
}
