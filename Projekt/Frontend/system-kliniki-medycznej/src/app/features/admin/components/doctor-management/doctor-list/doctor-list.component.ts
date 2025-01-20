import {Component, Input, OnInit} from '@angular/core';
import {DoctorInfo} from '../../../../doctor/domain/doctor-info';
import {DoctorService} from '../../../../doctor/services/doctor.service';
import {MatDialog} from '@angular/material/dialog';
import {CreateDoctorComponent} from '../create-doctor/create-doctor.component';
import {ActivatedRoute, Router} from '@angular/router';
import {RegisterDoctorForm} from '../../../../doctor/domain/register-doctor-form';
import {DoctorsTableComponent} from '../../../../doctor/components/doctor-table/doctors-table/doctors-table.component';
import {MessageDialogComponent} from '../../../../message/message-dialog/message-dialog.component';

@Component({
  selector: 'app-doctor-list',
  standalone: true,
  imports: [
    DoctorsTableComponent
  ],
  templateUrl: './doctor-list.component.html',
  styleUrl: './doctor-list.component.css'
})
export class DoctorListComponent implements OnInit{
  doctors: DoctorInfo[] = [];
  paginationOptions: { page: number; pageSize: number } = {
    page: 0,
    pageSize: 10,
  }

  ngOnInit() {
    this.doctorService.getPagedDoctors(this.paginationOptions).subscribe((data) => {
      console.log(data);
      this.doctors = data;
    })
  }

  constructor(
    private doctorService: DoctorService,
    private dialog: MatDialog,
    private router: Router,
  ) {
  }

  openAddDoctorModal() {
    this.dialog.open(CreateDoctorComponent).afterClosed().subscribe((doctorToRegisterFormData) => {
      if(doctorToRegisterFormData){
        let {profileImage,...doctorToRegister} = doctorToRegisterFormData;
        console.log(doctorToRegister,profileImage);
        this.doctorService.create(doctorToRegister,profileImage).subscribe({
          next: registeredDoctor => {
            this.addToArray(registeredDoctor);
            this.dialog.open(MessageDialogComponent, {
              data: {
                message: 'Utworzono lekarza!',
                type: 'success'
              }
            })
          },
          error: err => {
            this.dialog.open(MessageDialogComponent, {
              data: {
                message: err.error,
                type: 'error'
              }
            })
          }
        })
      }
    });
  }

  showDoctorDetails(doctor: DoctorInfo) {
    this.router.navigate(['/admin/doctors',doctor.id]);
  }

  private addToArray(registeredDoctor: DoctorInfo) {
    this.doctors.push(registeredDoctor);
  }
}
