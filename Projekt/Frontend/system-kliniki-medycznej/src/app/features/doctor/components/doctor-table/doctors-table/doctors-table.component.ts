import {Component, EventEmitter, Input, Output} from '@angular/core';
import {DoctorInfo} from '../../../domain/doctor-info';
import {MatDialog} from '@angular/material/dialog';
import {DoctorInfoDialogComponent} from '../../../dialogs/doctor-info-dialog/doctor-info-dialog.component';
import {UserService} from '../../../../auth/services/user.service';
import {Router} from '@angular/router';

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

  constructor(
    private dialog: MatDialog,
    private userService: UserService,
    private router: Router
  ) {
  }

  showDoctorDetails(doctor: DoctorInfo) {
    this.dialog.open(DoctorInfoDialogComponent,{
      data: {
        doctorInfo: doctor,
        showCreateAppointmentButton: true
      },
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

  navigate(doctorInfo: DoctorInfo) {
    if(this.userService.hasRole('ADMIN')){
      this.navigateToDoctorDetails(doctorInfo);
    }else{
      this.showDoctorDetails(doctorInfo);
    }
  }
  navigateToDoctorDetails(doctor: DoctorInfo) {
    this.router.navigate(['/admin/doctors',doctor.pwzNumber]);
  }
}
