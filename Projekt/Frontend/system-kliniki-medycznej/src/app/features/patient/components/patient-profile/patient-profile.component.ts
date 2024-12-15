import {Component, Input, OnInit} from '@angular/core';
import {PatientData} from '../../model/patient-data';
import {PatientsService} from '../../services/patients.service';
import {AccountComponent} from '../../../account/components/account/account.component';
import {HeaderTitleComponent} from '../../../header-title/header-title.component';
import {
  PersonalDetailsComponent
} from '../../../personal-details/components/personal-details/personal-details.component';
import {AccountInfo} from '../../../account/model/account-info';
import {AccountRolesComponent} from '../../../account-role/components/account-roles/account-roles.component';
import {UserService} from '../../../auth/services/user.service';
import {HttpStatusCode} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-patient-profile',
  standalone: true,
  imports: [
    AccountComponent,
    HeaderTitleComponent,
    PersonalDetailsComponent,
    AccountRolesComponent
  ],
  templateUrl: './patient-profile.component.html',
  styleUrl: './patient-profile.component.css'
})
export class PatientProfileComponent implements OnInit {
  @Input() id!: number;
  patientData!: PatientData;
  patientAccount!: AccountInfo;

  constructor(
    private patientsService: PatientsService,
    public userService: UserService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.patientsService.findPatientData(this.id).subscribe(
      {
        next: foundPatient => {
          this.patientData = foundPatient;
        },
        error: err => console.log(`ERROR`,err)
      }
    );
    this.patientsService.findPatientAccount(this.id).subscribe(data => {
      this.patientAccount = data;
    });
  }

  get getAccountInfo() {
    return this.patientAccount;
  }

  get getPersonalDetails() {
    console.log(this.patientData);
    return this.patientData.personalDetails;
  }

  deletePatient() {
    this.patientsService.deleteById(this.id).subscribe(response => {
      if(response.status === HttpStatusCode.Ok){
        this.router.navigate(['../'],{relativeTo: this.activatedRoute});
      }
    });

  }
}
