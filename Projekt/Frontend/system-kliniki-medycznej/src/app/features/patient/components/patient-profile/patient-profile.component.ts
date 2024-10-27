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
  patientDetails!: PatientData;
  patientAccount!: AccountInfo;

  constructor(
    private patientsService: PatientsService,
  ) {
  }

  ngOnInit(): void {
    this.patientsService.findPatientDetails(this.id).subscribe(
      {
        next: foundPatient => {
          this.patientDetails = foundPatient;
        },
        error: err => console.log(err)
      }
    );
  }

  get getAccountInfo() {
    if (!this.patientAccount) {
      this.patientsService.findPatientAccount(this.id).subscribe(data => {
        this.patientAccount = data;
      });
    }
    return this.patientAccount;
  }

  get getPersonalDetails() {
    return this.patientDetails.personalDetails;
  }
}
