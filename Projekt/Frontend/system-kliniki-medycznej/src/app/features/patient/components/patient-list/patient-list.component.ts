import {Component, Input, OnInit} from '@angular/core';
import {PatientData} from '../../model/patient-data';
import {PatientsService} from '../../services/patients.service';
import {Router} from '@angular/router';
import {Pagination} from '../../../pagination/model/pagination';
import {MatDialog} from '@angular/material/dialog';
import {CreatePatientDialogComponent} from '../../dialogs/create-patient-dialog/create-patient-dialog.component';
import {MessageDialogComponent} from '../../../message/message-dialog/message-dialog.component';

@Component({
  selector: 'app-patient-list',
  standalone: true,
  imports: [],
  templateUrl: './patient-list.component.html',
  styleUrl: './patient-list.component.css'
})
export class PatientListComponent implements OnInit {
  patients: PatientData[] = [];
  pagination: Pagination = {
    page: 0,
    pageSize: 10
  }

  constructor(
    private patientsService: PatientsService,
    private router: Router,
    private dialog: MatDialog
  ) {
  }


  ngOnInit(): void {
    this.patientsService.getAllPatientsPaged(this.pagination).subscribe(
      patients => {
        this.patients = patients;
      }
    )
  }


  showPatientDetails(patient: PatientData) {
    this.router.navigate(["admin/patients", patient.id]);
  }

  addPatient() {
    this.dialog.open(CreatePatientDialogComponent).afterClosed().subscribe(
      data => {
        this.createPatient(data);
      }
    )
  }

  private createPatient(data: any) {
    let {parentPesel,...personalDetails} = data;
    let patientToCreate = this.patientsService.buildPatientData(parentPesel,personalDetails);
    console.log(patientToCreate);
    this.patientsService.createPatient(patientToCreate).subscribe({
      next: createdPatient => {
        this.patients.unshift(createdPatient);
        this.dialog.open(MessageDialogComponent,{
          data: {
            message: 'Pomyślnie utworzono użytkownika',
            type:'success'
          }
        });
      },
      error: err => {
        this.dialog.open(MessageDialogComponent,{
          data: {
            message: err.error,
            type:'error'
          }
        });
      }
    });
  }
}
