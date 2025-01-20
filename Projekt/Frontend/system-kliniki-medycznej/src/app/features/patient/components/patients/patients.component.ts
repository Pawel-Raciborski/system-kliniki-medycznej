import {Component, Input, OnInit} from '@angular/core';
import {PatientData} from '../../model/patient-data';
import {PatientsService} from '../../services/patients.service';
import {Pagination} from '../../../pagination/model/pagination';
import {DatePipe} from '@angular/common';
import {PatientSearchBarComponent} from '../patient-search-bar/patient-search-bar.component';
import {SearchPatient} from '../../model/search-patient';
import {ActivatedRoute, Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {CreatePatientDialogComponent} from '../../dialogs/create-patient-dialog/create-patient-dialog.component';
import {UserService} from '../../../auth/services/user.service';
import {
  CreatePatientAccountDialogComponent
} from '../../../account/dialogs/create-patient-account-dialog/create-patient-account-dialog.component';
import {CreateAccountRequest} from '../../../account/model/create-account-request';
import {AccountService} from '../../../account/services/account.service';
import {MessageDialogComponent} from '../../../message/message-dialog/message-dialog.component';

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
    private router: Router,
    private dialog: MatDialog,
    public userService: UserService,
    private accountService: AccountService
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
    if(this.userService.hasRole("DOCTOR")){
      console.log('DOCTOR NAVIGATE');
      this.router.navigate(['doctor-panel','patients',patient.id,'patient-card']);
    } else if(
      this.userService.hasRole("RECEPTIONIST")
    ){
      this.router.navigate(['receptionist-panel','patients',patient.id,'patient-card']);
    } else if(this.userService.hasRole("ADMIN")){
      this.router.navigate(['admin','patients',patient.id]);
    }
  }

  isMale(gender: string | null) {
    if(gender){
      return gender === 'Mężczyzna';
    }
    return false;
  }

  setGenderClass(gender: string | null) {
    if(gender === 'Mężczyzna'){
      return 'bi bi-gender-male';
    } else if(gender === 'Kobieta'){
      return 'bi bi-gender-female';
    }
    return '';
  }

  searchPatient(searchPatient: SearchPatient) {
    this.patientsService.searchPatient(searchPatient).subscribe(
      data => {
        this.patients = data;
      }
    )
  }

  addPatient() {
    this.dialog.open(CreatePatientDialogComponent, {
      minWidth: '600px'
    }).afterClosed().subscribe(data => {
      if(data){
        this.createPatient(data);
      }
    });
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

  openCreateAccountModal() {
    this.dialog.open(CreatePatientAccountDialogComponent,{
      minWidth: '600px'
    }).afterClosed().subscribe((accountToCreate: CreateAccountRequest) => {
      if(accountToCreate){
        this.patientsService.createPatientAccount(accountToCreate).subscribe({
          next: value => {
            this.dialog.open(MessageDialogComponent,{
              data: {
                message: `Pomyślnie utworzono konto użytkownika o adresie ${value.accountDto.email}`,
                type: 'success'
              }
            });
          },
        error: err => {
          this.dialog.open(MessageDialogComponent,{
            data: {
              message: err.error,
              type: 'error'
            }
          });
        }
        });
      }
    })
  }
}
