import {Component, OnInit} from '@angular/core';
import {DoctorSearchBarComponent} from '../search-bar/doctor-search-bar/doctor-search-bar.component';
import {FormGroup} from '@angular/forms';
import {DoctorInfo} from '../../domain/doctor-info';
import {DoctorService} from '../../services/doctor.service';

@Component({
  selector: 'app-available-doctors',
  standalone: true,
  imports: [
    DoctorSearchBarComponent
  ],
  templateUrl: './available-doctors.component.html',
  styleUrl: './available-doctors.component.css'
})
export class AvailableDoctorsComponent implements OnInit {
  doctors: DoctorInfo[] = [];
  page = 0;
  pageSize = 10;

  constructor(private doctorService: DoctorService) {
  }

  ngOnInit(): void {
    this.doctorService.getPagedDoctors(this.page,this.pageSize).subscribe(doctors => {
      this.doctors = doctors;
    })
  }

  searchDoctors(formGroup: FormGroup) {
    this.doctorService.getDoctorsWithSpecifiedOptions(formGroup.value).subscribe(
      filteredDoctorSpecializations => {
        this.doctors = filteredDoctorSpecializations;
      }
    )
  }
}
