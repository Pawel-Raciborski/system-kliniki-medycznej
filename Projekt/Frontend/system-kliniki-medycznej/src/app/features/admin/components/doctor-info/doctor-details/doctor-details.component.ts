import {Component, Input, OnInit} from '@angular/core';
import {DoctorService} from '../../../../doctor/services/doctor.service';
import {DoctorDetails} from '../../../../doctor/domain/doctor-details';
import {HeaderTitleComponent} from '../../../../header-title/header-title.component';
import {MatCardTitle} from '@angular/material/card';
import {AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {MatRadioButton, MatRadioGroup} from '@angular/material/radio';
import {NgOptimizedImage} from '@angular/common';
import {ChangePassword} from '../../../../model/change-password';
import {DoctorSpecialization} from '../../../../doctor/domain/doctor-specialization';
import {MatDialog} from '@angular/material/dialog';
import {
  OfficeHoursDetailsDialogComponent
} from '../../../../doctor/dialogs/office-hours-details-dialog/office-hours-details-dialog.component';
import {OfficeHours} from '../../../../doctor/domain/office-hours';

@Component({
  selector: 'app-doctor-details',
  standalone: true,
  imports: [
    HeaderTitleComponent,
    MatCardTitle,
    ReactiveFormsModule,
    MatRadioButton,
    MatRadioGroup,
    NgOptimizedImage
  ],
  templateUrl: './doctor-details.component.html',
  styleUrl: './doctor-details.component.css'
})
export class DoctorDetailsComponent implements OnInit {
  @Input() pwzNumber !: number;
  doctorDetails!: DoctorDetails;
  doctorDetailsForm!: FormGroup;


  constructor(
    private doctorService: DoctorService,
    private formBuilder: FormBuilder,
    private dialog: MatDialog
    ) {
  }

  ngOnInit(): void {
    this.doctorService.findByPwzNumber(this.pwzNumber).subscribe(doctorDetails => {
      this.doctorDetailsForm = this.buildDoctorDetailsForm(doctorDetails);
      console.log(this.doctorDetailsForm);
    });
  }

  private buildDoctorDetailsForm(doctorDetails: DoctorDetails) {
    return this.formBuilder.group({
      username: new FormControl(doctorDetails.username),
      email: new FormControl(doctorDetails.email),
      personalDetails: this.formBuilder.group({
        pesel: new FormControl(doctorDetails.personalDetails.pesel),
        name: new FormControl(doctorDetails.personalDetails.name),
        surname: new FormControl(doctorDetails.personalDetails.surname),
        birthDate: new FormControl(this.formatDate(doctorDetails.personalDetails.birthDate)),
        gender: new FormControl(doctorDetails.personalDetails.gender),
        phoneNumber: new FormControl(doctorDetails.personalDetails.phoneNumber),
        address: this.formBuilder.group({
          street: new FormControl(doctorDetails.personalDetails.address.street),
          apartmentNumber: new FormControl(doctorDetails.personalDetails.address.apartmentNumber),
          postalCode: new FormControl(doctorDetails.personalDetails.address.postalCode),
          city: new FormControl(doctorDetails.personalDetails.address.city),
        })
      }),
      description: new FormControl(doctorDetails.description),
      doctorSpecializations: this.formBuilder.array(doctorDetails.doctorSpecializations.map(dS => new FormControl(dS))),
      doctorOfficeHours: this.formBuilder.array(doctorDetails.doctorOfficeHours.map(dH => new FormGroup({
        day: new FormControl(dH.day),
        startHour: new FormControl(dH.startHour),
        endHour: new FormControl(dH.endHour),
        durationInMinutes: new FormControl(dH.durationInMinutes),
      }))),
      pwzNumber: new FormControl(doctorDetails.pwzNumber),
      dateOfEmployment: new FormControl(doctorDetails.dateOfEmployment),
      changePasswordForm: this.formBuilder.group({
        currentPassword: new FormControl(''),
        newPassword: new FormControl(''),
        confirmPassword: new FormControl(''),
      })
    });
  }

  private formatDate(birthDate: string) {
    return birthDate.split("-").reverse().join("-");
  }

  changePassword() {
    console.log(this.doctorDetailsForm.controls['changePasswordForm'].value);

  }

  get doctorOfficeHours() : FormArray<FormGroup<{
    day: FormControl<string>;
    startHour: FormControl<string>;
    endHour: FormControl<string>;
    durationInMinutes: FormControl<number>;
  }>> {
    return this.doctorDetailsForm.controls['doctorOfficeHours'] as FormArray;
  }

  showDetails(officeHourForm: FormGroup<{
    day: FormControl<string>;
    startHour: FormControl<string>;
    endHour: FormControl<string>;
    durationInMinutes: FormControl<number>
  }>) {
    this.dialog.open(OfficeHoursDetailsDialogComponent,{
      data: officeHourForm
    })
  }
}
