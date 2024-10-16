import {Component, Input, OnInit} from '@angular/core';
import {DoctorInfo} from '../../../../doctor/domain/doctor-info';
import {DoctorService} from '../../../../doctor/services/doctor.service';
import {MatDialog} from '@angular/material/dialog';
import {CreateDoctorComponent} from '../create-doctor/create-doctor.component';

@Component({
  selector: 'app-doctor-list',
  standalone: true,
  imports: [],
  templateUrl: './doctor-list.component.html',
  styleUrl: './doctor-list.component.css'
})
export class DoctorListComponent implements OnInit{
  doctors: DoctorInfo[] = [];
  page:number = 0;
  pageSize: number = 10;

  ngOnInit() {
    this.doctorService.getPagedDoctors(this.page,this.pageSize).subscribe((data) => {
      this.doctors = data;
    })
  }

  constructor(
    private doctorService: DoctorService,
    private dialog: MatDialog
  ) {
  }

  openAddDoctorModal() {
    this.dialog.open(CreateDoctorComponent);
  }
}
