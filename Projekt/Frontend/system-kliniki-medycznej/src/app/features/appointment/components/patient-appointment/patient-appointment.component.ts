import {Component, OnInit} from '@angular/core';
import {PatientAppointmentInfo} from '../../model/patient-appointment-info';
import {AppointmentService} from '../../services/appointment.service';
import {UserService} from '../../../auth/services/user.service';
import {PatientAppointmentInfoComponent} from '../patient-appointment-info/patient-appointment-info.component';
import {PatientAppointmentsTableComponent} from '../patient-appointments-table/patient-appointments-table.component';
import {PatientsService} from '../../../patient/services/patients.service';

@Component({
  selector: 'app-patient-appointment',
  standalone: true,
  imports: [
    PatientAppointmentInfoComponent,
    PatientAppointmentsTableComponent
  ],
  templateUrl: './patient-appointment.component.html',
  styleUrl: './patient-appointment.component.css'
})
export class PatientAppointmentComponent implements OnInit{
  patientAppointments!: PatientAppointmentInfo[];
  patientPesel!: string;
  constructor(
    private appointmentService: AppointmentService,
    private userService: UserService,
    private patientService: PatientsService
  ) {
  }

  ngOnInit(): void {
    this.patientService.findPatientPesel(this.userService.email).subscribe(
      pesel => {
        this.patientPesel = pesel;
      }
    );

    this.appointmentService.findPatientUpcomingAppointments(this.patientPesel)
      .subscribe(data => {
        this.patientAppointments = data;
      });
  }

}
