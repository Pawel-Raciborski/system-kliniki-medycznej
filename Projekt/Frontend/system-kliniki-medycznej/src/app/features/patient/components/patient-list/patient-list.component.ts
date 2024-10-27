import {Component, Input, OnInit} from '@angular/core';
import {PatientData} from '../../model/patient-data';
import {PatientsService} from '../../services/patients.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-patient-list',
  standalone: true,
  imports: [],
  templateUrl: './patient-list.component.html',
  styleUrl: './patient-list.component.css'
})
export class PatientListComponent implements OnInit{
  patients: PatientData[] = [];
  page: number = 0;
  pageSize: number = 10;

  constructor(
    private patientsService: PatientsService,
    private router: Router
  ) {
  }


  ngOnInit(): void {
    this.patientsService.getAllPatientsPaged(this.page,this.pageSize).subscribe(
      patients => {
        this.patients = patients;
      }
    )
  }


  showPatientDetails(patient: PatientData) {
    this.router.navigate(["admin/patients",patient.id]);
  }
}
