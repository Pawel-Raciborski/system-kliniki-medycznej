import {Component, EventEmitter, Input, Output} from '@angular/core';
import {DoctorInfo} from '../../../domain/doctor-info';
import {MatDialog} from '@angular/material/dialog';
import {DoctorInfoDialogComponent} from '../../../dialogs/doctor-info-dialog/doctor-info-dialog.component';

@Component({
  selector: 'app-doctors-table',
  standalone: true,
  imports: [],
  templateUrl: './doctors-table.component.html',
  styleUrl: './doctors-table.component.css'
})
export class DoctorsTableComponent {
  @Input({required:true}) doctors: DoctorInfo[] = [];
  @Output() notificationMessageEmitter = new EventEmitter<{appointmentInfo:any, doctor: {name:string, surname: string}}>();

  constructor(private dialog: MatDialog) {
  }

  showDoctorDetails(doctor: DoctorInfo) {
    this.dialog.open(DoctorInfoDialogComponent,{
      data: doctor,
      width: '800px'
    }).afterClosed().subscribe(notification => {
      this.notificationMessageEmitter.emit({
        appointmentInfo: notification,
        doctor: {
          name: doctor.name,
          surname: doctor.surname
        }
      });
    });
  }
}