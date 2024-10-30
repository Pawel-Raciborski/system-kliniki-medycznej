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

  findPatientUpcomingAppointments(patientPesel: string):Observable<PatientAppointmentInfo[]> {
    const arr = [
      {
        id: "c0a8012c-7e6b-4e8f-900e-2e003d00c001",
        doctorName: "Emily",
        doctorSurname: "Jones",
        appointmentDate: "2024-11-12T14:30:00Z",
        appointmentStatus: "Pending"
      },
      {
        id: "c0a8012c-7e6b-4e8f-900e-2e003d00c002",
        doctorName: "Michael",
        doctorSurname: "Smith",
        appointmentDate: "2024-11-13T09:00:00Z",
        appointmentStatus: "Confirmed"
      },
      {
        id: "c0a8012c-7e6b-4e8f-900e-2e003d00c003",
        doctorName: "Sarah",
        doctorSurname: "Brown",
        appointmentDate: "2024-11-14T11:15:00Z",
        appointmentStatus: "Canceled"
      },
      {
        id: "c0a8012c-7e6b-4e8f-900e-2e003d00c004",
        doctorName: "John",
        doctorSurname: "Davis",
        appointmentDate: "2024-11-15T16:45:00Z",
        appointmentStatus: "Pending"
      },
      {
        id: "c0a8012c-7e6b-4e8f-900e-2e003d00c005",
        doctorName: "Jessica",
        doctorSurname: "Wilson",
        appointmentDate: "2024-11-16T13:30:00Z",
        appointmentStatus: "Confirmed"
      },
      {
        id: "c0a8012c-7e6b-4e8f-900e-2e003d00c006",
        doctorName: "Robert",
        doctorSurname: "Martinez",
        appointmentDate: "2024-11-17T10:00:00Z",
        appointmentStatus: "Pending"
      },
      {
        id: "c0a8012c-7e6b-4e8f-900e-2e003d00c007",
        doctorName: "Laura",
        doctorSurname: "Garcia",
        appointmentDate: "2024-11-18T15:45:00Z",
        appointmentStatus: "Completed"
      },
    ];

    return of(arr);
  }
}
