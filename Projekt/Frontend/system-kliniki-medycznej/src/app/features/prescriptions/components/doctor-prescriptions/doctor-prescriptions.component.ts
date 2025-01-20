import {Component, OnInit} from '@angular/core';
import {PrescriptionsTableComponent} from '../prescriptions-table/prescriptions-table.component';
import {TableOptionsComponent} from '../../../doctor/components/doctor-table/table-options/table-options.component';
import {Pagination} from '../../../pagination/model/pagination';
import {PrescriptionInfo} from '../../model/prescription-info';
import {PrescriptionService} from '../../services/prescription.service';
import {UserService} from '../../../auth/services/user.service';
import {PatientSearchBarComponent} from '../../../patient/components/patient-search-bar/patient-search-bar.component';
import {SearchPatient} from '../../../patient/model/search-patient';
import {MatDialog} from '@angular/material/dialog';
import {
  AddPrescriptionMedicinesDialogComponent
} from '../../dialogs/create-prescription-dialog/add-prescription-medicines-dialog.component';
import {PrescriptionMedicine} from '../../model/prescription-medicine';
import {CreatePrescriptionRequest} from '../../model/create-prescription-request';

@Component({
  selector: 'app-doctor-prescriptions',
  standalone: true,
  imports: [
    PrescriptionsTableComponent,
    TableOptionsComponent,
    PatientSearchBarComponent
  ],
  templateUrl: './doctor-prescriptions.component.html',
  styleUrl: './doctor-prescriptions.component.css'
})
export class DoctorPrescriptionsComponent implements OnInit {
  paginationOptions: Pagination = {
    page: 0,
    pageSize: 10
  };
  lastLoadedPageSize = this.paginationOptions.pageSize;
  prescriptions: PrescriptionInfo[] = [];

  constructor(
    private prescriptionService: PrescriptionService,
    private userService: UserService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
        this.loadMore();
  }

  loadMore() {
    this.prescriptionService.getPrescriptions(
      this.paginationOptions
    ).subscribe(data => {
        this.prescriptions = [...this.prescriptions,...data];
        this.lastLoadedPageSize = data.length;
    });
  }

  searchPrescriptionsForPatient(searchPatient: SearchPatient) {
    this.clearPagination();
    this.prescriptionService.findAllPrescriptionsForPatient(searchPatient)
      .subscribe(data => {
        this.prescriptions = data;
      })
  }

  private clearPagination() {
    this.paginationOptions.page = 0;
    this.paginationOptions.pageSize = 10;
  }

  openCreatePrescriptionDialog() {
    this.dialog.open(AddPrescriptionMedicinesDialogComponent).afterClosed()
      .subscribe((data) => {
        let prescriptionToCreate = this.createPrescription(data);
        this.prescriptionService.createPrescription(prescriptionToCreate).subscribe(
          createdPrescription => {
            this.prescriptions.unshift(createdPrescription);
          }
        )
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

    return prescription;
  }

}
