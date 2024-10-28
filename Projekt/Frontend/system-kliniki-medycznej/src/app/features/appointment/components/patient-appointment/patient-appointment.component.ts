import {Component, OnInit} from '@angular/core';
import {PatientAppointmentInfo} from '../../model/patient-appointment-info';
import {AppointmentService} from '../../services/appointment.service';
import {UserService} from '../../../auth/services/user.service';
import {PatientAppointmentInfoComponent} from '../patient-appointment-info/patient-appointment-info.component';

@Component({
  selector: 'app-patient-appointment',
  standalone: true,
  imports: [
    PatientAppointmentInfoComponent
  ],
  templateUrl: './patient-appointment.component.html',
  styleUrl: './patient-appointment.component.css'
})
export class PatientAppointmentComponent implements OnInit{
  appointmentInfo!: PatientAppointmentInfo;

  constructor(
    private appointmentService: AppointmentService,
    private userService: UserService
  ) {
  }

  ngOnInit(): void {
    this.appointmentService.findPatientNextAppointment(this.userService.email)
      .subscribe(data => {
        this.appointmentInfo = data;
      });
  }

}
