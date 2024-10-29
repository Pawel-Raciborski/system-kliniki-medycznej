import {Component, Input} from '@angular/core';
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

  constructor(private dialog: MatDialog) {
  }

  showDoctorDetails(doctor: DoctorInfo) {
    this.dialog.open(DoctorInfoDialogComponent,{
      data: doctor,
      width: '800px'
    });
  }
}
