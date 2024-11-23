import {Component, Input, OnInit} from '@angular/core';
import {AppointmentService} from '../../../services/appointment.service';
import {AppointmentDetails} from '../../../model/appointment-details';
import {PatientDataComponent} from '../../../../patient/components/patient-data/patient-data.component';
import {MatDialog} from '@angular/material/dialog';
import {
  CreatePrescriptionDialogComponent
} from '../../../../prescriptions/dialogs/create-prescription-dialog/create-prescription-dialog.component';

@Component({
  selector: 'app-appointment',
  standalone: true,
  imports: [
    PatientDataComponent
  ],
  templateUrl: './appointment.component.html',
  styleUrl: './appointment.component.css'
})
export class AppointmentComponent implements OnInit{
  @Input() id!: string;
  appointmentDetails!: AppointmentDetails;

  constructor(
    private appointmentService: AppointmentService,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.appointmentService.getAppointmentDetails(this.id).subscribe(
      data => {
        this.appointmentDetails = data;
      }
    )
  }

  openCreatePrescriptionDialog() {
    this.dialog.open(CreatePrescriptionDialogComponent,{
      minWidth:'600px'
    })
  }
}
