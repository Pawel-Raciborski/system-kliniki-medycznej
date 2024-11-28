import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {PatientAppointmentInfo} from '../model/patient-appointment-info';
import {AppointmentDetails} from '../model/appointment-details';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  arr = [
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

  constructor() {
  }

  findPatientNextAppointment(patientId: string): Observable<PatientAppointmentInfo> {
    let patientAppointmentInfo: PatientAppointmentInfo = {
      id: "c0a8012c-7e6b-4e8f-900e-2e003d00c000",
      doctorName: "Emily",
      doctorSurname: "Jones",
      appointmentDate: "2024-11-12T14:30:00Z",
      appointmentStatus: "Pending"
    };

    return of(patientAppointmentInfo);
  }

  createAppointment(appointmentDateTime: {
    patientPesel: string,
    date: string,
    hour: string,
    doctorPwzNumber: string
  }) {
    console.log(appointmentDateTime);
  }

  findPatientUpcomingAppointments(patientId: number, pageProperties: {
    page: number,
    pageSize: number
  }): Observable<PatientAppointmentInfo[]> {
    console.log('PATIENTID:', patientId, pageProperties);
    let slice = this.arr.slice(pageProperties.page * pageProperties.pageSize, (pageProperties.page + 1) * pageProperties.pageSize);
    console.log(`SLICE: ${slice.length}`);
    return of(slice);
  }

  findPatientFinishedAppointments(patientId: number, pageProperties: {
    page: number;
    pageSize: number
  }): Observable<PatientAppointmentInfo[]> {
    console.log(patientId);
    let slice = this.arr.slice(pageProperties.page * pageProperties.pageSize, (pageProperties.page + 1) * pageProperties.pageSize);
    console.log(`SLICE: ${slice.length}`);
    return of(slice);
  }

  getAppointmentDetails(appointmentId: string): Observable<AppointmentDetails> {
    const appDetails: AppointmentDetails = {
      appointment: {
        id: "ddd582b7-1f39-4118-b9e0-6ae1dc8e2d41",
        date: "2024-11-18",
        hour: "09:30",
        status: "Umówiono"
      },
      patientData: {
        id: 2,
        personalDetails: {
          pesel: "01249381623",
          name: "Piotr",
          surname: "Piotrowski",
          birthDate: "2001-04-20",
          gender: "Mężczyzna",
          phoneNumber: "+48 174 625 512",
          address: {
            street: "ul.Jana Pawła II",
            apartmentNumber: "5",
            postalCode: "12-492",
            city: "Białystok"
          }
        },
        parentPesel: "null"
      },
      patientCardId: "e7597d41-27b5-4a96-b529-6ff264bd4097"
    };

    return of(appDetails);
  }
}
