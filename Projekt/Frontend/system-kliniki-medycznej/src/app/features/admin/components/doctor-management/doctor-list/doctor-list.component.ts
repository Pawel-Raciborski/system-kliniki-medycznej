import {Component, Input, OnInit} from '@angular/core';
import {DoctorInfo} from '../../../../doctor/domain/doctor-info';
import {DoctorService} from '../../../../doctor/services/doctor.service';
import {MatDialog} from '@angular/material/dialog';
import {CreateDoctorComponent} from '../create-doctor/create-doctor.component';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-doctor-list',
  standalone: true,
  imports: [],
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
    this.dialog.open(CreateDoctorComponent).afterClosed().subscribe(data => {
      console.log(data);
    });
  }

  showDoctorDetails(doctor: DoctorInfo) {
    this.router.navigate(['/admin/doctors',doctor.pwzNumber]);
  }
}
