import {Component, OnInit} from '@angular/core';
import {PrescriptionsTableComponent} from '../prescriptions-table/prescriptions-table.component';
import {TableOptionsComponent} from '../../../doctor/components/doctor-table/table-options/table-options.component';
import {Pagination} from '../../../pagination/model/pagination';
import {PrescriptionInfo} from '../../model/prescription-info';
import {PrescriptionService} from '../../services/prescription.service';
import {UserService} from '../../../auth/services/user.service';
import {PatientSearchBarComponent} from '../../../patient/components/patient-search-bar/patient-search-bar.component';
import {SearchPatient} from '../../../patient/model/search-patient';

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
  ) {
  }

  ngOnInit(): void {
        this.loadMore();
  }

  loadMore() {
    this.prescriptionService.getDoctorPrescriptions(
      this.userService.getId("doctorId"),
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
}
