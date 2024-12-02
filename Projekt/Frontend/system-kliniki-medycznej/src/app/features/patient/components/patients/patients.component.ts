import {Component, Input, OnInit} from '@angular/core';
import {PatientData} from '../../model/patient-data';
import {PatientsService} from '../../services/patients.service';
import {Pagination} from '../../../pagination/model/pagination';
import {DatePipe} from '@angular/common';
import {PatientSearchBarComponent} from '../patient-search-bar/patient-search-bar.component';
import {SearchPatient} from '../../model/search-patient';
import {Router} from '@angular/router';

@Component({
  selector: 'app-patients',
  standalone: true,
  imports: [
    DatePipe,
    PatientSearchBarComponent
  ],
  templateUrl: './patients.component.html',
  styleUrl: './patients.component.css'
})
export class PatientsComponent implements OnInit{
  patients!: PatientData[];
  pagination:Pagination = {
    page: 0,
    pageSize: 10,
  }

  constructor(
    private patientsService: PatientsService,
    private router: Router
  ) {
  }
  ngOnInit(): void {
    this.patientsService.getAllPatientsPaged(this.pagination).subscribe(
      data => {
        this.patients = data;
      }
    )
  }

  showPatientDetails(patient: PatientData) {
    this.router.navigate(['doctor-panel','patients',patient.id,'patient-card']);
  }

  isMale(gender: string | null) {
    if(gender){
      return gender === 'male';
    }
    return false;
  }

  setGenderClass(gender: string | null) {
    console.log(gender);
    if(this.isMale(gender)){
      return 'bi bi-gender-male';
    }
    return 'bi bi-gender-female';
  }

  searchPatient(searchPatient: SearchPatient) {
    this.patientsService.searchPatient(searchPatient).subscribe(
      data => {
        this.patients = data;
      }
    )
  }
}
