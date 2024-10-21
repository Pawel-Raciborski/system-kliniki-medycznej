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
    MatMenuItem
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
    private dialog: MatDialog,
    private officeHoursService: OfficeHoursService,
    public userService: UserService,
    private localStorageService: LocalStorageService,
    private doctorSpecializationService: DoctorSpecializationService
  ) {
    this.localStorageService.saveMockedData();

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
      doctorSpecializations: this.formBuilder.array(doctorDetails.doctorSpecializations.map(doctorSpecialization => this.buildDoctorSpecializationFormGroup(doctorSpecialization))),
      doctorOfficeHours: this.formBuilder.array(doctorDetails.doctorOfficeHours.map(doctorOfficeHours => this.buildDoctorOfficeHoursForm(doctorOfficeHours))),
      pwzNumber: new FormControl(doctorDetails.pwzNumber),
      dateOfEmployment: new FormControl(doctorDetails.dateOfEmployment),
      changePasswordForm: this.formBuilder.group({
        currentPassword: new FormControl(''),
        newPassword: new FormControl(''),
        confirmPassword: new FormControl(''),
      })
    });
  }

  private buildDoctorOfficeHoursForm(dH: OfficeHours) {
    return new FormGroup({
      day: new FormControl(dH.day),
      startHour: new FormControl(dH.startHour),
      endHour: new FormControl(dH.endHour),
      durationInMinutes: new FormControl(dH.durationInMinutes),
    });
  }

  private formatDate(birthDate: string) {
    return birthDate.split("-").reverse().join("-");
  }

  changePassword() {
    console.log(this.doctorDetailsForm.controls['changePasswordForm'].value);

  }

  get doctorOfficeHours(): FormArray<FormGroup<{
    day: FormControl<string | null>;
    startHour: FormControl<string | null>;
    endHour: FormControl<string | null>;
    durationInMinutes: FormControl<number | null>;
  }>> {
    return this.doctorDetailsForm.controls['doctorOfficeHours'] as FormArray;
  }

  get doctorSpecializations(): FormArray<FormGroup<{name: FormControl<string | null>, description: FormControl<string | null>, realizedDate: FormControl<string | null>}>>{
    return this.doctorDetailsForm.controls['doctorSpecializations'] as FormArray;
  }

  showOfficeHourDetails(officeHourForm: FormGroup<{
    day: FormControl<string | null>;
    startHour: FormControl<string | null>;
    endHour: FormControl<string | null>;
    durationInMinutes: FormControl<number | null>
  }>) {
    localStorage.setItem('officeHours', JSON.stringify(officeHourForm.value));

    let doctorOfficeHoursDialogData: DoctorOfficeHoursDialogData = {
      doctorOfficeHoursForm: officeHourForm,
      isNewData: false
    }

    this.dialog.open(OfficeHoursDetailsDialogComponent, {
      data: doctorOfficeHoursDialogData
    }).afterClosed().subscribe((officeHours: OfficeHours) => {
      console.log(officeHours);
      this.officeHoursService.update(officeHours).subscribe({
          next: officeHours => {
            if(officeHours){
              this.setOfficeHoursFormValues(officeHourForm, officeHours);
            }
          },
          error: err => {
            console.error(err);
            let officeHoursLocalStorage = localStorage.getItem('officeHours');

            if (officeHoursLocalStorage) {
              const officeHours: OfficeHours = JSON.parse(officeHoursLocalStorage);

              this.setOfficeHoursFormValues(officeHourForm, officeHours);
              localStorage.removeItem('officeHours');
            }
          }
        }
      );
    });
  }

  private setOfficeHoursFormValues(officeHourForm: FormGroup<{
    day: FormControl<string | null>;
    startHour: FormControl<string | null>;
    endHour: FormControl<string | null>;
    durationInMinutes: FormControl<number | null>
  }>, officeHours: OfficeHours) {
    officeHourForm.setValue({...officeHours});
  }

  delete(i: number) {
    let doctorOfficeHoursArray = this.doctorOfficeHours;

    let dayToRemove = doctorOfficeHoursArray.at(i).value.day;

    if (dayToRemove) {
      this.officeHoursService.delete(dayToRemove);
      doctorOfficeHoursArray.removeAt(i);
    }

  }

  addOfficeHours() {
    let doctorOfficeHoursDialogData: DoctorOfficeHoursDialogData = {
      doctorOfficeHoursForm: this.buildEmptyOfficeHoursForm(),
      isNewData: true
    }

    this.dialog.open(OfficeHoursDetailsDialogComponent, {
      data: doctorOfficeHoursDialogData
    }).afterClosed().subscribe((officeHoursToCreate: OfficeHours) => {
      if(officeHoursToCreate){
        this.officeHoursService.create(officeHoursToCreate);
        this.doctorOfficeHours.push(doctorOfficeHoursDialogData.doctorOfficeHoursForm);
        console.log(officeHoursToCreate);
      }
    })
  }

  private buildEmptyOfficeHoursForm() {
    return new FormGroup({
      day: new FormControl(''),
      startHour: new FormControl(''),
      endHour: new FormControl(''),
      durationInMinutes: new FormControl(''),
    }) as FormGroup;
  }

  private buildDoctorSpecializationFormGroup(doctorSpecialization: DoctorSpecialization) {
    return this.formBuilder.group({
      name: doctorSpecialization.name,
      description: doctorSpecialization.description,
      realizedDate: this.formatDate(doctorSpecialization.realizedDate)
    });
  }

  addDoctorSpecialization() {
    let doctorSpecializationForm = this.doctorSpecializationService.buildDoctorSpecialization();

    this.dialog.open(CreateDoctorSpecializationComponent,{
      data: doctorSpecializationForm
    }).afterClosed().subscribe((doctorSpecializationToCreate: DoctorSpecialization) => {
      this.doctorSpecializationService.create(doctorSpecializationToCreate).subscribe(
        (createdDoctorSpecialization: DoctorSpecialization) => {
          let form = this.buildDoctorSpecializationFormGroup(createdDoctorSpecialization);
          this.doctorSpecializations.push(form);
        }
      );
    })
  }

  showDoctorSpecializationDetails(doctorSpecializationsForm: FormGroup) {
    localStorage.setItem('doctorSpecialization', JSON.stringify(doctorSpecializationsForm.value));

    this.dialog.open(CreateDoctorSpecializationComponent, {
      data: doctorSpecializationsForm
    }).afterClosed().subscribe((doctorSpecialization: DoctorSpecialization) => {
      console.log(doctorSpecialization);
      this.doctorSpecializationService.update(doctorSpecialization).subscribe({
          next: updatedDoctorSpecialization => {
            if(updatedDoctorSpecialization){
              doctorSpecializationsForm.setValue({...updatedDoctorSpecialization});
            }
          },
          error: err => {
            console.error(err);
            let officeHoursLocalStorage = localStorage.getItem('doctorSpecialization');

            if (officeHoursLocalStorage) {
              const doctorSpecializationData: DoctorSpecialization = JSON.parse(officeHoursLocalStorage);

              doctorSpecializationsForm.setValue({...doctorSpecializationData});
              localStorage.removeItem('doctorSpecialization');
            }
          }
        }
      );
    })
  }

  deleteDoctorSpecialization(i: number) {
    let doctorSpecializationArray = this.doctorSpecializations;
    let specializationName = doctorSpecializationArray.at(i).value.name;

    if (specializationName) {
      this.doctorSpecializationService.delete(specializationName);
      doctorSpecializationArray.removeAt(i);
    }
  }
}
