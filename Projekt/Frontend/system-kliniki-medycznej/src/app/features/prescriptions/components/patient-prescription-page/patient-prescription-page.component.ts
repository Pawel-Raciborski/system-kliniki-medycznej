import {Component, OnInit} from '@angular/core';
import {PrescriptionsTableComponent} from '../prescriptions-table/prescriptions-table.component';
import {PrescriptionInfo} from '../../model/prescription-info';
import {PrescriptionService} from '../../services/prescription.service';
import {TableOptionsComponent} from '../../../doctor/components/doctor-table/table-options/table-options.component';

@Component({
  selector: 'app-patient-prescription-page',
  standalone: true,
  imports: [
    PrescriptionsTableComponent,
    TableOptionsComponent
  ],
  templateUrl: './patient-prescription-page.component.html',
  styleUrl: './patient-prescription-page.component.css'
})
export class PatientPrescriptionPageComponent implements OnInit {
  prescriptions: PrescriptionInfo[] = [];
  paginationOptions = {
    page: 0,
    pageSize: 10,
  }

  constructor(
    private prescriptionService: PrescriptionService
  ) {
  }

  ngOnInit(): void {
    this.loadPrescriptionData();
  }

  onPageSizeChange(newPageSize: number) {
    this.paginationOptions.pageSize = newPageSize;
    this.loadPrescriptionData();
  }

  private loadPrescriptionData() {
    this.prescriptionService.getPrescriptions(this.paginationOptions).subscribe(data => {
      this.prescriptions = data;
    });
  }
}
