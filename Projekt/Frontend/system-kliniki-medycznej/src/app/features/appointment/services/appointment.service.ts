import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {PatientAppointmentInfo} from '../model/patient-appointment-info';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor() {
  }

  findPatientNextAppointment(email: string): Observable<PatientAppointmentInfo> {
    let patientAppointmentInfo: PatientAppointmentInfo = {
      id: "c0a8012c-7e6b-4e8f-900e-2e003d00c000",
      doctorName: "Emily",
      doctorSurname: "Jones",
      appointmentDate: "2024-11-12T14:30:00Z",
      appointmentStatus: "Pending"
    };

    return of(patientAppointmentInfo);
  }

  createAppointment(appointmentDateTime: { patientPesel: string, date: string, hour: string, doctorPwzNumber: string }) {
    console.log(appointmentDateTime);
  }
}
