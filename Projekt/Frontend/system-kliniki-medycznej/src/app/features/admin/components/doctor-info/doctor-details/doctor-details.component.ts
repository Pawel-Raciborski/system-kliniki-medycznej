import {Component, Input, OnInit} from '@angular/core';
import {DoctorService} from '../../../../doctor/services/doctor.service';
import {DoctorDetails} from '../../../../doctor/domain/doctor-details';
import {HeaderTitleComponent} from '../../../../header-title/header-title.component';
import {MatCardTitle} from '@angular/material/card';
import {FormArray, FormBuilder, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MatRadioButton, MatRadioGroup} from '@angular/material/radio';
import {NgOptimizedImage} from '@angular/common';
import {MatDialog} from '@angular/material/dialog';
import {
  OfficeHoursDetailsDialogComponent
} from '../../../../doctor/dialogs/office-hours-details-dialog/office-hours-details-dialog.component';
import {OfficeHours} from '../../../../doctor/domain/office-hours';
import {OfficeHoursService} from '../../../../doctor/services/office-hours.service';
import {MatMenu, MatMenuItem, MatMenuTrigger} from '@angular/material/menu';
import {DoctorOfficeHoursDialogData} from '../../../../doctor/dialogs/models/doctor-office-hours-dialog-data';
import {UserService} from '../../../../auth/services/user.service';
import {LocalStorageService} from '../../../../auth/services/local-storage.service';
import {DoctorSpecialization} from '../../../../doctor/domain/doctor-specialization';
import {DoctorSpecializationService} from '../../../../doctor-specialization/services/doctor-specialization.service';
import {
  CreateDoctorSpecializationComponent
} from '../../doctor-management/create-doctor-specialization/create-doctor-specialization.component';
import {AccountRolesComponent} from '../../../../account-role/components/account-roles/account-roles.component';
import {AccountComponent} from '../../../../account/components/account/account.component';
import {
  PersonalDetailsComponent
} from '../../../../personal-details/components/personal-details/personal-details.component';
import {AccountInfo} from '../../../../account/model/account-info';
import {DoctorOfficeHoursComponent} from '../../../../office-hours/doctor-office-hours/doctor-office-hours.component';
import {
  DoctorSpecializationsComponent
} from '../../../../doctor-specialization/doctor-specializations/doctor-specializations.component';
import {HttpStatusCode} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-doctor-details',
  standalone: true,
  imports: [
    HeaderTitleComponent,
    MatCardTitle,
    ReactiveFormsModule,
    MatRadioButton,
    MatRadioGroup,
    NgOptimizedImage,
    MatMenuTrigger,
    MatMenu,
    MatMenuItem,
    AccountRolesComponent,
    AccountComponent,
    PersonalDetailsComponent,
    DoctorOfficeHoursComponent,
    DoctorSpecializationsComponent
  ],
  templateUrl: './doctor-details.component.html',
  styleUrl: './doctor-details.component.css'
})
export class DoctorDetailsComponent implements OnInit {
  @Input() id !: number;
  doctorDetails!: DoctorDetails;

  constructor(
    private doctorService: DoctorService,
    public userService: UserService,
    private localStorageService: LocalStorageService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.localStorageService.saveMockedData();

  }

  ngOnInit(): void {
    this.doctorService.findById(this.id).subscribe(doctorDetails => {
      this.doctorDetails = doctorDetails;
    });
  }

  getProfileImage(): string {
    return this.doctorDetails.profileImagePath;
  }

  public getAccountInfo(): AccountInfo {
    return {
      username: this.doctorDetails.username,
      email: this.doctorDetails.email,
    }
  }

  deleteDoctor() {
    this.doctorService.delete(this.doctorDetails.pwzNumber).subscribe(response => {
      if(response.status === HttpStatusCode.Ok){
        this.router.navigate(['../'],{relativeTo: this.activatedRoute});
      }
    });
  }
}
