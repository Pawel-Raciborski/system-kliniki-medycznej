import {Component, Input, OnInit} from '@angular/core';
import {AppointmentService} from '../../../services/appointment.service';
import {AppointmentDetails} from '../../../model/appointment-details';
import {PatientDataComponent} from '../../../../patient/components/patient-data/patient-data.component';
import {MatDialog} from '@angular/material/dialog';
import {
  AddPrescriptionMedicinesDialogComponent
} from '../../../../prescriptions/dialogs/create-prescription-dialog/add-prescription-medicines-dialog.component';
import {PrescriptionMedicine} from '../../../../prescriptions/model/prescription-medicine';
import {CreatePrescriptionRequest} from '../../../../prescriptions/model/create-prescription-request';
import {UserService} from '../../../../auth/services/user.service';
import {
  ShowPatientHospitalizationsDialogComponent
} from '../../../../patient-card/dialogs/show-patient-hospitalizations-dialog/show-patient-hospitalizations-dialog.component';
import {
  ShowCreatingPrescriptionDetailsDialogComponent
} from '../../../../prescriptions/dialogs/show-creating-prescription-details-dialog/show-creating-prescription-details-dialog.component';
import {
  CreateDiseaseDialogComponent
} from '../../../../disease/dialogs/create-disease-dialog/create-disease-dialog.component';
import {CreatePatientDiseaseRequest} from '../../../../patient-disease/create-patient-disease-request';
import {CreatePatientDiseasePart} from '../../../../disease/model/create-patient-disease-part';
import {
  AppointmentPatientDiseaseComponent
} from '../../../../disease/components/appointment-patient-disease/appointment-patient-disease.component';
import {FinishAppointmentRequest} from '../../../model/finish-appointment-request';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-appointment',
  standalone: true,
  imports: [
    PatientDataComponent,
    AppointmentPatientDiseaseComponent,
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './appointment.component.html',
  styleUrl: './appointment.component.css'
})
export class AppointmentComponent implements OnInit {
  form: FormGroup = new FormGroup({
    diagnosis: new FormControl('')
  });
  @Input() id!: string;
  appointmentDetails!: AppointmentDetails;
  prescriptions: CreatePrescriptionRequest[] = [];
  patientDiseases: CreatePatientDiseaseRequest[] = [];

  constructor(
    private appointmentService: AppointmentService,
    private dialog: MatDialog,
    public userService: UserService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
  ) {
  }

  ngOnInit(): void {
    this.appointmentService.getAppointmentDetails(this.id).subscribe(
      data => {
        console.log(data);
        this.appointmentDetails = data;
      }
    )
  }

  openCreatePrescriptionDialog() {
    this.dialog.open(AddPrescriptionMedicinesDialogComponent, {
      minWidth: '600px'
    }).afterClosed().subscribe((data: {
      expirationDate: string,
      description: string,
      prescriptionMedicines: PrescriptionMedicine[],
      patientPesel: string
    }) => {
      if (data) {
        this.createPrescription(data);
      }
    });
  }

  private createPrescription(data: {
    expirationDate: string;
    description: string;
    prescriptionMedicines: PrescriptionMedicine[],
    patientPesel: string
  }) {
    let prescriptionMedicines = data.prescriptionMedicines.map(p => {
      return {
        registryNumber: p.medicine.registryNumber,
        dosage: p.dosage
      };
    });

    let prescription: CreatePrescriptionRequest = {
      patientPesel: data.patientPesel,
      doctorId: this.userService.getId('doctorId'),
      prescriptionMedicineList: prescriptionMedicines,
      description: data.description,
      expirationDate: data.expirationDate,
    };

    this.prescriptions.push(prescription);
  }

  showHospitalizations() {
    this.dialog.open(ShowPatientHospitalizationsDialogComponent, {
      data: this.appointmentDetails.patientCardId,
      minWidth: '1200px'
    });
  }

  showPrescriptionMedicines(medicines: { registryNumber: string; dosage: string }[]) {
    this.dialog.open(ShowCreatingPrescriptionDetailsDialogComponent, {
      data: medicines
    })
  }

  openCreateDiseaseDialog() {
    this.dialog.open(CreateDiseaseDialogComponent).afterClosed().subscribe(
      (value: CreatePatientDiseasePart) => {
        if (value) {
          const patientDisease = this.buildPatientDisease(value);
          this.patientDiseases.push(patientDisease);
        }
      }
    )
  }

  private buildPatientDisease(value: CreatePatientDiseasePart): CreatePatientDiseaseRequest {
    return {
      diseaseDto: value.disease,
      description: value.description,
      doctorId: this.userService.getId('doctorId'),
      patientCardId: this.appointmentDetails.patientCardId,
      hospitalizations: value.hospitalizations
    }
  }

  finishAppointment() {
    let appointmentRequest = this.buildAppointmentRequest();

    this.appointmentService.finishAppointment(appointmentRequest).subscribe(
      data => {
        this.router.navigate(['../../calendar'], {relativeTo: this.activatedRoute});
      }
    );

  }

  private buildAppointmentRequest(): FinishAppointmentRequest {
    return {
      appointmentId: this.appointmentDetails.appointment.id,
      diagnosis: this.form.value.diagnosis,
      appointmentPrescriptions: this.prescriptions,
      patientDiseases: this.patientDiseases
    }
  }
}
