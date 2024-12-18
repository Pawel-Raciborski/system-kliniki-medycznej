import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {PatientAppointmentInfo} from '../model/patient-appointment-info';
import {AppointmentDetails} from '../model/appointment-details';
import {FinishAppointmentRequest} from '../model/finish-appointment-request';
import {AppointmentInfo} from '../model/appointment-info';
import {CalendarAppointmentsResponse} from '../../doctor/domain/calendar/calendar-appointments-response';
import {environment} from '../../../../environments/environment.dev';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  url = `${environment.serverUrl}/appointments`
  patientAppointmentsUrl = `${environment.serverUrl}/patient-appointments`;


  constructor(
    private httpClient: HttpClient
  ) {
  }

  createAppointment(appointmentDateTime: {
    patientPesel: string,
    date: string,
    hour: string,
    doctorPwzNumber: string
  }): Observable<AppointmentInfo> {
    return this.httpClient.post<AppointmentInfo>(
      `${this.url}/schedule-appointment`,
      appointmentDateTime
    );
  }

  findPatientUpcomingAppointments(patientId: number, pageProperties: {
    page: number,
    pageSize: number
  }): Observable<PatientAppointmentInfo[]> {
    return this.httpClient.get<PatientAppointmentInfo[]>(
      `${this.patientAppointmentsUrl}`,
      {
        params: {
          page: pageProperties.page,
          pageSize: pageProperties.pageSize,
          patientId: patientId
        }
      }
    );
  }

  findPatientFinishedAppointments(patientId: number, pageProperties: {
    page: number;
    pageSize: number
  }): Observable<PatientAppointmentInfo[]> {
    return this.httpClient.get<PatientAppointmentInfo[]>(
      `${this.patientAppointmentsUrl}/finished`,
      {
        params: {
          page: pageProperties.page,
          pageSize: pageProperties.pageSize,
          patientId: patientId
        }
      }
    );
  }

  getAppointmentDetails(appointmentId: string): Observable<AppointmentDetails> {
    return this.httpClient.get<AppointmentDetails>(
      `${this.url}/${appointmentId}/details`
    );
  }

  finishAppointment(appointmentRequest: FinishAppointmentRequest) {
    return this.httpClient.put<HttpResponse<void>>(
      `${this.url}/finish`,
      appointmentRequest
    );
  }

  getAvailableStatuses(): Observable<{ value: string, name: string }[]> {
    return this.httpClient.get<{ value: string, name: string }[]>(
      `${this.url}/available-statuses`
    );
  }

  updateStatus(appointmentId: string, newStatus: string): Observable<AppointmentInfo> {
    return this.httpClient.put<AppointmentInfo>(
      `${this.url}/update-status`,
      {},
      {
        params: {
          id: appointmentId,
          newStatus: newStatus
        }
      }
    )
  }

  findDoctorAppointments(formatType?: 'week' | 'month', id?: number, date?: string): Observable<CalendarAppointmentsResponse> {
    let httpParams = new HttpParams();

    if (formatType) {
      httpParams = httpParams.append("formatType", formatType);
    }
    if (id) {
      httpParams = httpParams.append("doctorId", id);
    }
    if (date) {
      httpParams = httpParams.append("date", date);
    }
    console.log(httpParams);
    return this.httpClient.get<CalendarAppointmentsResponse>(
      `${environment.serverUrl}/doctors/calendar/all-appointments`,
      {
        params: httpParams
      }
    );
  }
}
